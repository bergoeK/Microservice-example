package com.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.user.dto.EmployeeDTO;
import com.user.exception.ResourceNotFoundException;
import com.user.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

		return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		return ResponseEntity.ok().body(employeeService.getAllEmployees());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable Long id) {

		return employeeService.getEmployeeById(id)
				.map(employeeDTO -> new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id :" + id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeDTODetail,
			@PathVariable Long id) {

		try {
			return new ResponseEntity<>(employeeService.updateEmployee(employeDTODetail, id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

	}

	@DeleteMapping
	public ResponseEntity<Boolean> deleteEmployee(@RequestBody EmployeeDTO employeeDTO) {
		
		
		try {
			employeeService.deleteEmployee(employeeDTO);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}

		return ResponseEntity.ok().body(true);

	}


}
