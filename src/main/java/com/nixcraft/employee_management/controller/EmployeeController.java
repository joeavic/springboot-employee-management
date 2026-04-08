package com.nixcraft.employee_management.controller;

import com.nixcraft.employee_management.dto.EmployeeRequestDTO;
import com.nixcraft.employee_management.dto.EmployeeResponseDTO;
import com.nixcraft.employee_management.entity.Employee;
import com.nixcraft.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee
            (@RequestBody @Valid EmployeeRequestDTO e) {
        return ResponseEntity.ok(employeeService.addEmployee(e));
    }

    @DeleteMapping
    public ResponseEntity<Employee> deleteEmployee(@RequestParam UUID id){
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

}
