package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.configuration.H2JpaConfig;
import com.user.dto.EmployeeDTO;
import com.user.mapper.EmployeeMapper;
import com.user.model.Employee;
import com.user.repository.EmployeeRepository;

@SpringBootTest(classes = H2JpaConfig.class)
// Par defaut spring recherche application.properties ds le test/resources folder
//et mem ds le main/java/resource folder.
// utilisez testPropertySource ssi on veut utiliser un fichier autre ke application.properties

public class EmployeeServiceIntegrationTest {

	@Autowired
	private EmployeeService employeeServiceImpl;

	@Autowired
	EmployeeRepository employeeRepository;

//	@Autowired
//	UserController userController;

	@Autowired
	EmployeeMapper employeeMapper;

//	@Autowired
//	EmployeeController employeController;

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

//	@TestConfiguration
//	@ComponentScan(basePackageClasses = { EmployeeMapper.class, EmployeeRepository.class, EmployeeServiceImpl.class })
//	static class Config {
//
//	}

}
