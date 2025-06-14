package com.sample.gateway.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InternalGatewayException extends RuntimeException {
    public InternalGatewayException(String message) {
        super(message);
    }

    public InternalGatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
