package nurse;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
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
public class ScheduleControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testRequestSchedule() throws Exception {
		this.mockMvc.perform(post("/schedule").contentType(MediaType.APPLICATION_JSON).content(
				"{\n" + 
				"  \"workers\": [\n" + 
				"    {\n" + 
				"      \"id\": 1,\n" + 
				"      \"availability\": [\"Tuesday\"],\n" + 
				"      \"payrate\": 7.50\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"id\": 2,\n" + 
				"      \"availability\": [\"Monday\"],\n" + 
				"      \"payrate\": 9.00\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"id\": 3,\n" + 
				"      \"availability\": [\"Friday\"],\n" + 
				"      \"payrate\": 8.00\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"shifts\": [\n" + 
				"    {\n" + 
				"      \"id\": 1,\n" + 
				"      \"day\": [\"Monday\"]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"id\": 2,\n" + 
				"      \"day\": [\"Tuesday\"]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"id\": 3,\n" + 
				"      \"day\": [\"Friday\"]\n" + 
				"    }\n" + 
				"  ]\n" + 
				"}\n" + 
				""
				)).andDo(print()).andExpect(status().isOk())
		        .andExpect(jsonPath("suboptimal", is(true)));
	}

}
