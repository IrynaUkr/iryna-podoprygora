package com.epam.cashier.controller.service.mapper;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);

    List<UserDto> mapUserDtos(List<User> users);

}
