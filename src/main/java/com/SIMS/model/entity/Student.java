package com.SIMS.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Student {
    @Id
    private String studentId;
    private List<String> courses;
}
