package com.example.demoTest.model;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Student {
    @Id
    private String studentId;
    private String firstName;
    private String lastName;
}
