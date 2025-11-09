package com.example.registrationservice.repositories;

import com.example.registrationservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
