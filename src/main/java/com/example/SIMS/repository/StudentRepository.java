package com.example.SIMS.repository;

import com.example.SIMS.model.entity.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository {
    Profile saveProfile(Profile profile);
    Profile getProfileByEmail(String email);
    List<String> getAllStudents();
    Profile getStudentById(String studentId);
    Profile updateProfile(Profile profile) throws Exception;
}
