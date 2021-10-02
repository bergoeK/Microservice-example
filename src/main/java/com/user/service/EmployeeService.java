package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.dto.EmployeeDTO;
import com.user.exception.ResourceNotFoundException;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployees();

	Optional<EmployeeDTO> getEmployeeById(Long id);

	EmployeeDTO createEmployee(EmployeeDTO user);

	EmployeeDTO updateEmployee(EmployeeDTO employeDTODetail, Long id) throws ResourceNotFoundException;

	void deleteEmployee(EmployeeDTO employeeDTO) throws ResourceNotFoundException;


}
