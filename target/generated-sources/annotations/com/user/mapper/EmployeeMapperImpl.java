package com.user.mapper;

import com.user.dto.EmployeeDTO;
import com.user.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-02T10:52:21+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO toDto(Employee employe) {
        if ( employe == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setNachname( employe.getLastName() );
        employeeDTO.setId( employe.getId() );
        employeeDTO.setFirstName( employe.getFirstName() );
        employeeDTO.setEmail( employe.getEmail() );

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> toDtoList(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( toDto( employee ) );
        }

        return list;
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setLastName( employeeDTO.getNachname() );
        employee.setId( employeeDTO.getId() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setEmail( employeeDTO.getEmail() );

        return employee;
    }
}
