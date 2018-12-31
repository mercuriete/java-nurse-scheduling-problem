package nurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.SolutionCollector;
import com.google.ortools.constraintsolver.Solver;
import com.mercuriete.nurse.model.Match;
import com.mercuriete.nurse.model.Matching;
import com.mercuriete.nurse.model.Shift;
import com.mercuriete.nurse.model.Shifts;
import com.mercuriete.nurse.model.Worker;
import com.mercuriete.nurse.model.Workers;

@Service
public class NSPResolver {
	static {
		System.loadLibrary("jniortools");
	}

	@Autowired
	Logger logger;

	Matching resolve(Shifts shifts, Workers workers) {
		// Creates the solver.
		Solver solver = new Solver("schedule_shifts");

		IntVar[] shiftFlat = new IntVar[shifts.size()];
		int i = 0;
		for (Shift s : shifts) {
			// for all shift we have to create a integer variable
			// with domain available workers

			// Search available workers for this shift
			ArrayList<Long> availableworkers = new ArrayList<>();
			for (Worker w : workers) {
				if (w.getAvailability().contains(s.getDay().get(0))) {
					availableworkers.add(w.getId());
				}
			}

			// the available workers are the domain of the Integer Variable
			shiftFlat[i++] = solver.makeIntVar(toArrayPrimitive(availableworkers), s.getDay().toString());
		}

		// Create the decision builder
		DecisionBuilder db = solver.makePhase(shiftFlat, Solver.CHOOSE_FIRST_UNBOUND, Solver.ASSIGN_MIN_VALUE);
		// Create the solution collector.
		Assignment solution = solver.makeAssignment();

		solution.add(shiftFlat);
		SolutionCollector collector = solver.makeAllSolutionCollector(solution);

		solver.solve(db, collector);
		logger.info(() -> "Solutions found: " + collector.solutionCount());
		logger.info(() -> "Time: " + solver.wallTime() + " ms");

		// Print all Solutions but return only first
		Matching output = new Matching();
		for (int sol = 0; sol < collector.solutionCount(); sol++) {
			final int tempsol = sol;
			logger.info(() -> "Solution number: " + tempsol);
			for (i = 0; i < shiftFlat.length; i++) {
				if (sol == 0) {
					// only return first valid solution
					Match m = new Match();
					m.setShiftId((long) (i + 1));
					m.setWorkerId(collector.value(sol, shiftFlat[i]));
					output.add(m);
				}
				final int tempi = i;
				logger.info(
						() -> "Shift: " + (tempi + 1) + ", assigned to: " + collector.value(tempsol, shiftFlat[tempi]));
			}
		}
		return output;
	}

	public static long[] toArrayPrimitive(final List<Long> list) {
		return unboxed(list.toArray(new Long[0]));
	}

	public static long[] unboxed(final Long[] array) {
		return Arrays.stream(array).filter(Objects::nonNull).mapToLong(Long::longValue).toArray();
	}
}
