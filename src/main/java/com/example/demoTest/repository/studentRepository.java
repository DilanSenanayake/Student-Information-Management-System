package com.example.demoTest.repository;

import com.example.demoTest.model.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository {
    Profile saveProfile(Profile profile);
}
