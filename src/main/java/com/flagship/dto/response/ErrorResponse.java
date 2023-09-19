package com.flagship.dto.response;

public class ErrorResponse {
    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }
    public Integer getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}