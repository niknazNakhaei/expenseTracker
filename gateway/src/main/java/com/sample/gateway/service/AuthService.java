package com.sample.gateway.service;

import com.sample.gateway.dto.request.LoginDto;
import com.sample.gateway.dto.request.UserDto;
import com.sample.gateway.dto.response.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "user", url = "localhost:9090/auth")
public interface AuthService {

    @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
    LoginResponse register(UserDto userDto);

    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    LoginResponse login(LoginDto loginDto);
}
