package com.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.user.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//If y use @BeforeAll and @AfterAll you will annoted the class with 
//@TestInstance(Lifecycle.PER_CLASS) with  
// by default @datajpaTest is  rollback true
public class EmployeeRepositoryMysqlTest {

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
		System.out
				.println("+++############## The using database  URL is  #######  "
						+ dataSource.getConnection().getMetaData().getURL().toString());
	}

	@Test
	void injectComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(employeeRepository).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();

	}

	// testAddNew(), testListAll(), test
	@Test
	void shouldSaveEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setEmail("Nkapa@yahoo.fr");
		employee.setFirstName("Nkapa Michelle");
		employee.setLastName("Nkapa Nonou12");

		Employee savedEmployee = employeeRepository.save(employee);
		assertThat(savedEmployee).isNotNull();
		assertThat(employee.getId()).isNotNull();
		assertThat(employee.getEmail()).isEqualTo("Nkapa@yahoo.fr");
		assertThat(savedEmployee).usingRecursiveComparison().ignoringFields("id", "").isEqualTo(employee);

	}

	void testUpdate() {
		Long employeId = 1l;
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeId);
		Employee employe = optionalEmployee.map((emp) -> {
			emp.setEmail("");
			emp.setFirstName("");
			return emp;
		}).orElse(new Employee());
		employeeRepository.save(employe);

		Employee updatedEmploye = employeeRepository.findById(employeId).get();

		assertThat(updatedEmploye.getEmail()).isEqualTo("");
	}

	void testLoadById() {
		Long employeId = 1l;
		Optional<Employee> optionalEmploye = employeeRepository.findById(employeId);
		assertThat(optionalEmploye).isPresent();
	}

	void testDelete() {
		Long employeId = 2l;
		employeeRepository.deleteById(employeId);
		Optional<Employee> optionalEmploye = employeeRepository.findById(employeId);
		assertThat(optionalEmploye).isNotPresent();
	}

	// @Test
//	@Sql("classpath:test-data.sql")
	void shouldSaveEmployeesThroughSqlFile() {
		Employee employe = employeeRepository.findEmployeeByEmail("");
		assertThat(employe).isNotNull();

	}
}

