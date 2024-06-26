package com.fasols.chatapp.mapper;

import com.fasols.chatapp.dto.request.UserRequestDTO;
import com.fasols.chatapp.dto.response.UserResponseDTO;
import com.fasols.chatapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO userToUserResponseDTO(User user);
    User userRequestdtoToUser(UserRequestDTO userRequest);
}
