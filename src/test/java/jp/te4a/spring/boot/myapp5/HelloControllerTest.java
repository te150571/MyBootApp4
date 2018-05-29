package jp.te4a.spring.boot.myapp5;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HelloControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void before()throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}
	
	@Test
	public void HelloIndexTest()throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("msg"))
			.andExpect(model().attribute("msg", is("This is setting message.")))
			.andExpect(view().name("index"));
	}
	
	@Test
	public void HelloPostTextTest()throws Exception {
		mockMvc.perform(post("/post").param("text1", "abc"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("msg"))
			.andExpect(model().attribute("msg", is("you write 'abc'!!")))
			.andExpect(view().name("index"));
	}
	
	@Test
	public void HelloPostNoTextTest()throws Exception {
		mockMvc.perform(post("/post").param("text1", ""))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("msg"))
			.andExpect(model().attribute("msg", is("you write ''!!")))
			.andExpect(view().name("index"));
	}
	
	@Test
	public void HelloPostErrorTest()throws Exception {
		mockMvc.perform(post("/post"))
			.andExpect(status().isBadRequest());
	}
}
