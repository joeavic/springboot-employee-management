package com.nixcraft.employee_management.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID Id;

    private String name;

    private double salary;

}
