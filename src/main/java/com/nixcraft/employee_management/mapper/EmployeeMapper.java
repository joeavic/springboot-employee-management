package com.nixcraft.employee_management.mapper;

import com.nixcraft.employee_management.dto.EmployeeRequestDTO;
import com.nixcraft.employee_management.dto.EmployeeResponseDTO;
import com.nixcraft.employee_management.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDTO dto){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    public static EmployeeResponseDTO toEmployeeResponseDTO(Employee employee){
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .salary(employee.getSalary())
                .build();
    }
}
