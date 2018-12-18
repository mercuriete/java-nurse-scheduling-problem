package nurse;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercuriete.nurse.api.ScheduleApi;
import com.mercuriete.nurse.model.Matching;
import com.mercuriete.nurse.model.ScheduleRequest;
import com.mercuriete.nurse.model.ScheduleResponse;

@RestController
public class ScheduleController implements ScheduleApi {

	@Override
	public ResponseEntity<ScheduleResponse> schedulePost(@Valid @RequestBody ScheduleRequest body) {
		ScheduleResponse info = new ScheduleResponse();
		info.setSuboptimal(true);
		info.setMatching(new Matching());
		return new ResponseEntity<>(info, HttpStatus.OK);
	}

}
