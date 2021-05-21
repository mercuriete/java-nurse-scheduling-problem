package nurse;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ScheduleControllerTests {

	@Autowired
	private MockMvc mockMvc;

	private String loadResource(String path) {
		try (Scanner in = new Scanner(this.getClass().getResourceAsStream(path), "UTF-8")) {
			return in.useDelimiter("\\A").next();
		}
	}

	@Test
	void testRequestScheduleExample1() throws Exception {
		this.mockMvc
				.perform(post("/schedule").contentType(MediaType.APPLICATION_JSON)
				.content(loadResource("../example_1.json")))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("suboptimal", is(true)));
	}

	@Test
	void testRequestScheduleExample2() throws Exception {
		this.mockMvc
				.perform(post("/schedule").contentType(MediaType.APPLICATION_JSON)
				.content(loadResource("../example_2.json")))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("suboptimal", is(true)));
	}

}
