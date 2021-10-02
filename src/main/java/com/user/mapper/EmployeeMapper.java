package com.user.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.user.dto.EmployeeDTO;
import com.user.model.Employee;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	@Mapping(source = "lastName", target = "nachname")
	// @Mapping(target = "id", ignore = true)
	EmployeeDTO toDto(Employee employe);

	List<EmployeeDTO> toDtoList(List<Employee> employees);

	@InheritInverseConfiguration
	Employee toEntity(EmployeeDTO employeeDTO);

}
