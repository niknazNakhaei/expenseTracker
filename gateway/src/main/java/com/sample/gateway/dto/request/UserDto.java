package com.sample.gateway.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String name;
    private String email;
}
