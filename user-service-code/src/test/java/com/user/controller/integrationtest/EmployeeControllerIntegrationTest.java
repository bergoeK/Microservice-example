package com.user.controller.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import com.user.dto.EmployeeDTO;

//https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	EmployeeDTO employeeDTO;

	@BeforeEach
	void init() {

		employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail("tkamdem@yahoo.fr");
		employeeDTO.setFirstName("Simon");
		employeeDTO.setNachname("Mangwassa");

	}

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/employees/";
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllEmployees() {
//		HttpHeaders httpHeaders = new HttpHeaders();
//		HttpEntity<String> entity = new HttpEntity<String>(null, httpHeaders);
		List<EmployeeDTO> employeeDTO = testRestTemplate.getForObject(getRootUrl(), List.class);
		assertNotNull(employeeDTO);
	}

	@Test
	void testGetEmployeeDetails() {
		int id = 1;
		EmployeeDTO employeeDTO = testRestTemplate.getForObject(getRootUrl() + id, EmployeeDTO.class);
//		ResponseEntity<EmployeeDTO> response = testRestTemplate.getForEntity(getRootUrl(), EmployeeDTO.class);
//		EmployeeDTO emp = response.getBody();
		assertNotNull(employeeDTO);
	}

	@Test
	void testSaveEmployee() {
		
//		ResponseEntity<EmployeeDTO> postResponse = testRestTemplate.postForEntity(getRootUrl(), employeeDTO,
//				EmployeeDTO.class);

		ResponseEntity<EmployeeDTO> response = testRestTemplate.exchange(getRootUrl(), HttpMethod.POST,
				new HttpEntity<>(employeeDTO), EmployeeDTO.class);

		assertNotNull(response.getBody());
	}
	
	@Test
	@Disabled
	void testUpdateEmployee() {
		// https://stackoverflow.com/questions/33242126/spring-resttemplate-put-entity-to-server
		int id = 11;
		employeeDTO.setFirstName("Blandine");
		employeeDTO.setNachname("Mefoe");
		employeeDTO.setEmail("Blandine@yahoo.fr");

		ResponseEntity<EmployeeDTO> response = testRestTemplate.exchange(getRootUrl() + id, HttpMethod.PUT,
				new HttpEntity<EmployeeDTO>(employeeDTO), EmployeeDTO.class);
		EmployeeDTO updatedEmployeeDTO = response.getBody();
		assertNotNull(updatedEmployeeDTO);
		assertEquals(employeeDTO.getEmail(), updatedEmployeeDTO.getEmail());
		assertEquals(employeeDTO.getFirstName(), updatedEmployeeDTO.getFirstName());
		assertEquals(employeeDTO.getNachname(), updatedEmployeeDTO.getNachname());


	}
	
	@Test
	@Disabled
	public void testDeleteEmployee() {
		Long id = 6l;
		String url = getRootUrl();
		employeeDTO.setId(id);

		try {
			ResponseEntity<Boolean> response = testRestTemplate.exchange(url, HttpMethod.DELETE,
					new HttpEntity<>(employeeDTO),
					Boolean.class);
			assertNotNull(response);
			assertTrue(response.getBody());
		} catch (ResponseStatusException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatus());

		}

	}


	@Test
	void testDeleteEmployeeThrowsRestClientException() {
		// https://blog.oio.de/2019/12/11/junit-5-behandlung-von-exceptions/
		Long id = 6l;
		String url = getRootUrl();
		employeeDTO.setId(id);
		Assertions.assertThrows(RestClientException.class, () -> {
			ResponseEntity<Boolean> response = testRestTemplate.exchange(url, HttpMethod.DELETE,
					new HttpEntity<>(employeeDTO),
					Boolean.class);
			assertNotNull(response);
			assertTrue(response.getBody());
	     }); 
	}

}
