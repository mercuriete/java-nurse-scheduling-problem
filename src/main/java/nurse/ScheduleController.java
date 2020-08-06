package nurse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercuriete.nurse.api.ScheduleApi;
import com.mercuriete.nurse.model.Match;
import com.mercuriete.nurse.model.ScheduleRequest;
import com.mercuriete.nurse.model.ScheduleResponse;

import java.util.List;

@RestController
public class ScheduleController implements ScheduleApi {

	@Autowired
	NSPResolver nspResolver;
	@Override
	public ResponseEntity<ScheduleResponse> schedulePost(@Valid @RequestBody ScheduleRequest body) {

		List<Match> mat = nspResolver.resolve(body.getShifts(), body.getWorkers());

		ScheduleResponse info = new ScheduleResponse();
		info.setSuboptimal(true);
		info.setMatching(mat);
		return new ResponseEntity<>(info, HttpStatus.OK);
	}

}
