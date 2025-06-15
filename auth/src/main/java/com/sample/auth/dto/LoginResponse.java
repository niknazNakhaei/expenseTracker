package com.sample.auth.dto;

public record LoginResponse(String token, String name, String email) {
}
