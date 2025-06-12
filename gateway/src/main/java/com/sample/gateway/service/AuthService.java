package com.sample.gateway.service;

import com.sample.gateway.dto.request.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "user", url = "localhost:9090/user")
public interface AuthService {

    @PostMapping(path = "/register", produces = "application/json")
    void userRegister(UserDto userDto);
}
