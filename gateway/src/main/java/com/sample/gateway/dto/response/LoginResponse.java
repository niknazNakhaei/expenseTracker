package com.sample.gateway.dto.response;

public record LoginResponse(String token, String name, String email) {
}
