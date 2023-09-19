package com.flagship.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final Integer code;
    public UserNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
