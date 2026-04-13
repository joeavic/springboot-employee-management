package com.nixcraft.employee_management.service;

import com.nixcraft.employee_management.dto.EmployeeRequestDTO;
import com.nixcraft.employee_management.dto.EmployeeResponseDTO;
import com.nixcraft.employee_management.entity.Employee;
import com.nixcraft.employee_management.kafka.event.EmployeeEvent;
import com.nixcraft.employee_management.kafka.producer.EmployeeProducer;
import com.nixcraft.employee_management.mapper.EmployeeMapper;
import com.nixcraft.employee_management.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeProducer employeeProducer;

    public List<EmployeeResponseDTO> getEmployees() {

        log.info("Request to get all Employees Data received {}", Instant.now());

        List<Employee> employeeList = employeeRepository.findAll();

        List<String> nameList = employeeList.stream().map(Employee::getName).toList();

        log.info("Employee List {}", nameList);

        return employeeList.stream()
                .map(EmployeeMapper::toEmployeeResponseDTO).toList();
    }

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO e) {
        log.info("EmployeeReq received {}", e);
        Employee employee = EmployeeMapper.toEntity(e);
        Employee saved = employeeRepository.save(employee);
        log.info("Employee Saved ");

        // kafka event generation
        EmployeeEvent event = new EmployeeEvent(
                "CREATED",
                saved.getId(),
                saved.getName(),
                saved.getSalary()
        );
        employeeProducer.publishEmployeeCreated(event);

        return EmployeeMapper.toEmployeeResponseDTO(saved);
    }

    public Employee deleteEmployee(UUID id) {
        Employee e = null;
        if(employeeRepository.findById(id).isPresent()){
            e = employeeRepository.findById(id).get();
            employeeRepository.delete(e);
        }
        return e;
    }

}
