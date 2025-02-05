package com.study.authentication.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.study.authentication.dtos.UserDTO;
import com.study.authentication.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Ignora o atributo "tarefas" ao mapear User para UserDTO
    @Mapping(target = "tarefas", ignore = true)
    UserDTO toUserDTO(User user);

    // Mapeia de UserDTO para User
    //User toUser(UserDTO userDTO);

    // Mapeia uma lista de User para uma lista de UserDTO
    List<UserDTO> toUserDTOList(List<User> users);

    // Mapeia uma lista de UserDTO para uma lista de User
    //List<User> toUserList(List<UserDTO> userDTOs);
}