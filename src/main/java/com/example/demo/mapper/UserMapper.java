package com.example.demo.mapper;

import org.mapstruct.Mapper;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

//Jembatan User ke UserDTO
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapper.class);

    // Map User to UserDTO
    UserDTO userToUserDTO(User user);
    // Map UserDTO to User
    User userDTOToUser(UserDTO userDTO);
}
