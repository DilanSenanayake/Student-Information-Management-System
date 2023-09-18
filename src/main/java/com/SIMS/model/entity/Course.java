package com.SIMS.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Course {
    @Id
    private String courseId;
    private String name;
    private List<String> students;

    public Course(String courseId, String name, List<String> students) {
        this.courseId = courseId;
        this.name = name;
        this.students = students;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
