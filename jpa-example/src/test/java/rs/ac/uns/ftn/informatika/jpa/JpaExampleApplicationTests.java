package rs.ac.uns.ftn.informatika.jpa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaExampleApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	/*@Test
	public void testOrder() throws Exception {

		this.mvc.perform(get("/orderr")).andExpect(status().isOk())
				.andExpect(content().string("kraj"));
	}*/
	
	
	@Test
	public void testCity() throws Exception {

		this.mvc.perform(get("/city")).andExpect(status().isOk())
				.andExpect(content().string("Bath"));
	}
	
	@Test
	public void testHotel() throws Exception {

		this.mvc.perform(get("/hotel")).andExpect(status().isOk())
				.andExpect(content().string("Fantastic place-Just lovely."));
	}

}
