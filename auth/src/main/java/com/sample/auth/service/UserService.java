package com.sample.auth.service;

import com.sample.auth.dto.LoginDto;
import com.sample.auth.dto.LoginResponse;
import com.sample.auth.dto.UserDto;

public interface UserService {

    LoginResponse register(UserDto userDto);

    LoginResponse login(LoginDto input);
}
