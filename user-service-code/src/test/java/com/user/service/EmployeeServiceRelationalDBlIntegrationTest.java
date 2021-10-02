package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.user.dto.EmployeeDTO;
import com.user.model.Employee;

//@SpringBootTest(classes = { H2JpaConfig.class })
public class EmployeeServiceRelationalDBlIntegrationTest {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@Test
	void saveTest() {
		Employee employee = new Employee();
		employee.setEmail("Karelkamdem@yahoo.fr");
		employee.setFirstName("Karelkamdem Nanou");
		employee.setLastName(" Karelkamdem Fotie");

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setNachname(employee.getLastName());

		employeeDTO = employeeServiceImpl.createEmployee(employeeDTO);

		assertEquals("Karelkamdem@yahoo.fr", employee.getEmail());
	}

}
