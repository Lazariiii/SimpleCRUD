package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Custom query to find users by name
    List<User> findByName(String name);

    // Custom query to find users by email
    Optional<User> findByEmail(String email);

    // Custom query to find users by name and email
    List<User> findByNameAndEmail(String name, String email);

    // Custom query to find all users
    default List<User> findAllUsers() {
        return new ArrayList<>(findAll());
    }
    
}
