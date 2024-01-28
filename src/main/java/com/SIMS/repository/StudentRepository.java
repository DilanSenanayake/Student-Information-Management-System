package com.SIMS.repository;

import com.SIMS.model.entity.Profile;
import com.mongodb.client.result.DeleteResult;
import java.util.List;

public interface StudentRepository {
    Profile saveProfile(Profile profile);
    Profile getProfileByEmail(String email);
    List<Profile> getAllStudents() throws Exception;
    Profile getStudentById(String studentId);
    Profile updateProfile(Profile profile) throws Exception;
    DeleteResult deleteStudent(String studentId) throws Exception;
}
