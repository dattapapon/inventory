package com.flagship.exception;

import lombok.Getter;

@Getter
public class UserExistException extends RuntimeException {
    private final Integer code;

    public UserExistException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
