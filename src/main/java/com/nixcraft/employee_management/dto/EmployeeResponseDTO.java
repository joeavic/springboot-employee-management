package com.nixcraft.employee_management.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
public class EmployeeResponseDTO {
    private final UUID id;
    private final String name;
    private final Double salary;
}
