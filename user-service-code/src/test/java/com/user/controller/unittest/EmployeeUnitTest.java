package com.user.controller.unittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.controller.EmployeeController;
import com.user.dto.EmployeeDTO;
import com.user.service.EmployeeService;

//https://www.logicbig.com/tutorials/unit-testing/mockito/mockito-argument-matchers.html
// https://www.youtube.com/watch?v=Fatt6nw_m6o : best
@WebMvcTest(EmployeeController.class)
public class EmployeeUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

// @WebMvcTest stellt ObjectMapper instance bereit. 
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmployeeService employeeService;

	// jsonRequest
	EmployeeDTO employeeDTOInput;

	EmployeeDTO expectedEmployeeDTO;

	@Test
	void shoulCreateMockMvc() {
		assertThat(mockMvc).isNotNull();
	}

	@BeforeEach
	void init() {
		// create MockMvc instance . if you use this, do not use @Autowired on MockMvc
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		expectedEmployeeDTO = new EmployeeDTO();
		expectedEmployeeDTO.setEmail("simon@yahoo.fr");
		expectedEmployeeDTO.setFirstName("Simon");
		expectedEmployeeDTO.setNachname("Mangwassa");

		// Do this in each test method.
		Mockito.when(employeeService.createEmployee(ArgumentMatchers.any())).thenReturn(expectedEmployeeDTO);

	}

	private String getRootUrl() {
		return "http://localhost:/api/employees/";
	}

	// https://www.youtube.com/watch?v=Aasp0mWT3Ac
	@Test
	void whenValidInput_thenReturns200() throws Exception {

		// mockMvc.perform(MockMvcRequestBuilders.delete/post/put/get(uri)))
		// status().isOk()
		mockMvc.perform(post(getRootUrl()).contentType(MediaType.APPLICATION_JSON_VALUE).header("", "")
				.content(objectMapper.writeValueAsString(employeeDTOInput)))
				.andExpect(status().isCreated());

	}

	// addEmployeTest
	@Test
	void whenValidInput_thenReturns2001() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post(getRootUrl()).contentType("application/json")
				.content(objectMapper.writeValueAsString(employeeDTOInput)))
				.andExpect(status().isCreated())
				.andReturn();
		// mvcResult.getResponse().getStatus()
		String actualEmployeeDTOString = mvcResult.getResponse().getContentAsString();

		assertThat(actualEmployeeDTOString)
				.isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(employeeDTOInput));

		EmployeeDTO actualEmployee = objectMapper.readValue(actualEmployeeDTOString, EmployeeDTO.class);
		assertEquals(expectedEmployeeDTO.getEmail(), actualEmployee.getEmail());


	}

//	@TestConfiguration
//	private static class Config {
//
//		@Bean
//		public ObjectMapper objectMapper() {
//			return new ObjectMapper();
//		}
//
//	}

}
