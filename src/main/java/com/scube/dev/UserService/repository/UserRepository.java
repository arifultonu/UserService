package com.scube.dev.UserService.repository;

import com.scube.dev.UserService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Role,Long> {
}