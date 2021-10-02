package com.user.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dto.EmployeeDTO;
import com.user.exception.ResourceNotFoundException;
import com.user.mapper.EmployeeMapper;
import com.user.model.Employee;
import com.user.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


	private final EmployeeRepository employeeRepository;

	// https://stackoverflow.com/questions/54774679/mapstruct-mocking-nested-mapper
	private final EmployeeMapper employeeMapper;


	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}


	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return employeeMapper.toDtoList(employeeRepository.findAll(EmployeeSpecification.nameLike("")));
//		return employeeMapper.map(employeeRepository.findAll());
	}


	@Override
	public Optional<EmployeeDTO> getEmployeeById(Long id) {

		EmployeeDTO employeeDTO = employeeMapper.toDto(employeeRepository.getOne(id));
		return Optional.ofNullable(employeeDTO);

	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.save(employeeMapper.toEntity(employeeDTO));
		return employeeMapper.toDto(employee);
	}


	@Override
	public void deleteEmployee(EmployeeDTO employeeDTO) throws ResourceNotFoundException {

		Employee employee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(
				() -> new ResourceNotFoundException(" No Employee found with the geving id " + employeeDTO.getId(),
						new Date()));
		employeeRepository.delete(employee);

	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeDTODetail, Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException(" No Employee found with the geving id " + id, new Date()));

		employee.setEmail(employeDTODetail.getEmail());
		employee.setFirstName(employeDTODetail.getFirstName());
		employee.setLastName(employeDTODetail.getNachname());
		return employeeMapper.toDto(employee);
	}

	private static class EmployeeSpecification {

		private static Specification<Employee> nameLike(String firstname) {
			return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstname"),
					"%" + firstname + "%");
		}

		private static org.springframework.data.jpa.domain.Specification<Employee> nameAndEmailLike(String firstname,
				String email) {

			return (root, query, criteriaBuilder) -> criteriaBuilder.and(
					criteriaBuilder.like(root.get("firstname"), "%" + firstname + "%"),
					criteriaBuilder.equal(root.get("email"), email),
					criteriaBuilder.in(root.get("email")).value(Arrays.asList("")));

			// criteriaBuilder.gt(expression, number / object));
			// criteriaBuilder.equal(expression, Object object)

		}
	}

}
