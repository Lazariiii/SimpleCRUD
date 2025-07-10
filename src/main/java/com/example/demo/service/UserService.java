package com.example.demo.service;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository; 

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    } 

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id %s", id)));     
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public UserDTO createUser(UserDTO user) {
        User newUser = UserMapper.INSTANCE.userDTOToUser(user);
        User savedUser = userRepository.save(newUser);
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }

    public UserDTO updateUser(UUID id, UserDTO updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id %s", id)));  
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        User updated = userRepository.save(existingUser);
        return UserMapper.INSTANCE.userToUserDTO(updated);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
