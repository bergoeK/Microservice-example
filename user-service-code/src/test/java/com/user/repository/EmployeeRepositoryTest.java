package com.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.user.model.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EntityManager entityManager;


	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setup() throws Exception {
		System.out.println("+++############## The using database  URL is  #######  "
				+ dataSource.getConnection().getMetaData().getURL().toString());

	}

	@Test
	void injectComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(employeeRepository).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
	}

	@Test
	void saveIsSucceful() {

		Employee employee = new Employee();
		employee.setEmail("Souza@yahoo.fr");
		employee.setFirstName("Souza Michelle");
		employee.setLastName("Souza Nonou12");

		Employee savedEmployee = employeeRepository.save(employee);
		assertNotNull(employee.getId());
		assertThat(employee.getId()).isNotNull();
		assertThat(employee.getEmail()).isEqualTo("Souza@yahoo.fr");
		assertThat(savedEmployee).usingRecursiveComparison().ignoringFields("id", "").isEqualTo(employee);

	}
}
