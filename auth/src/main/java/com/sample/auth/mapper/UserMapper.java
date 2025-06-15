package com.sample.auth.mapper;

import com.sample.auth.dto.UserDto;
import com.sample.auth.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

    User mapToEntity(UserDto dto);

    UserDto mapToDto(User dto);
}
