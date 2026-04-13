package com.nixcraft.employee_management.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvent
{
    private String eventType;
    private UUID Id;
    private String name;
    private double salary;
}
