package com.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.user.dto.EmployeeDTO;
import com.user.mapper.EmployeeMapper;
import com.user.model.Employee;
import com.user.repository.EmployeeRepository;

//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)

// use @ExtendWith(MockitoExtension.class) oder  MockitoAnnotations.openMocks(this); https://www.youtube.com/watch?v=Geq60OVyBPg
public class EmployeeServiceUnitTest {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
//	oder
//	private EmployeeService employeeServiceImpl;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private EmployeeMapper employeeMapper;




	@BeforeEach
	void setup() {
		/*
		 * Because we using a construct injection. We can either create the
		 * bean/instance by calling the constructor or using the @InjectMocks
		 */
//		employeeServiceImpl = new EmployeeServiceImpl(employeeRepository, employeeMapper);

		// use @ExtendWith(MockitoExtension.class) oder
		// MockitoAnnotations.openMocks(this);
		MockitoAnnotations.openMocks(this);
		// https://tedvinke.wordpress.com/2014/02/13/mockito-why-you-should-not-use-injectmocks-annotation-to-autowire-fields/
//		employeeServiceImpl = new EmployeeServiceImpl(employeeRepository, employeeMapper);

		Employee employee = new Employee();
		employee.setEmail("nanou12@yahoo.fr");
		employee.setFirstName("Nanou Michelle");
		employee.setLastName("Kamdem Nonou12");

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setNachname(employee.getLastName());
		// BDDMockito.given(employeeRepository.findAll()).willReturn(null);
		// Mockito.doReturn(Optional.empty()).when(doctorService).getById(1L);
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
		when(employeeMapper.toDtoList(ArgumentMatchers.anyList())).thenReturn(Arrays.asList(employeeDTO));
	}

	@Test
	@Disabled
	void getAllEmployeesTest() {
		MockitoAnnotations.openMocks(this);
		List<EmployeeDTO> employeeDTOs = employeeServiceImpl.getAllEmployees();
		assertNotNull(employeeDTOs);
		EmployeeDTO actualEmployeeDTO = employeeDTOs.get(0);
		assertEquals("nanou12@yahoo.fr", actualEmployeeDTO.getEmail());
		// verify that employeeRepository.findAll() is executed, when
		// employeeServiceImpl.getAllEmployees() is called ;
		verify(employeeRepository).findAll();
	}

	void CreateEmployeeTest() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail("nanou12@yahoo.fr");
		employeeDTO.setFirstName("Nanou Michelle");

		// when
		employeeServiceImpl.createEmployee(employeeDTO);

		// then
		ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

		verify(employeeRepository).save(employeeArgumentCaptor.capture());
		Employee capturedEmployee = employeeArgumentCaptor.getValue();

		assertThat(capturedEmployee).isEqualTo(employeeDTO);
	}

	// Tous les services externe cad injectés doivent etre ds le cadre des test
	// unitaire mocké.
	// https://www.logicbig.com/tutorials/unit-testing/mockito/bdd-style-stubbing-with-given-will-return.html
	// https://www.youtube.com/watch?v=Geq60OVyBPg
	void willThrowExceptionWhenEmailIsTaken() {
		// BDDMockito.given(employeeRepository.findAll()).willReturn(null);
		// when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
		assertThatThrownBy(() -> {
			// cette methode est supposéé envoye une exception si email n'est pas valide
			employeeServiceImpl.createEmployee(null);
		}).isInstanceOf(RuntimeException.class).hasMessageContaining(" errorMessage");
		verify(employeeRepository, never()).save(ArgumentMatchers.any());
	}

	@DisplayName("Should Throw Exception  when the Email is null")
	void shouldFailWhenCommentContainsShit() {

		RuntimeException rEx = assertThrows(RuntimeException.class, () -> {
			// cette methode est supposéé envoye une RuntimeException si email est null
			employeeServiceImpl.createEmployee(null);
		});

		assertTrue(rEx.getMessage().contains(""));

		// oder wit assertJ is much better
		assertThatThrownBy(() -> {
			// cette methode est supposéé envoye une RuntimeException si email est null
			employeeServiceImpl.createEmployee(null);
		}).isInstanceOf(RuntimeException.class).hasMessage("");
	}

}
