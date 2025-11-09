package com.example.registrationservice.repositories;

import com.example.registrationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
