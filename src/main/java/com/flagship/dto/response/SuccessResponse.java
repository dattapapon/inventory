package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Builder
@Data
public class SuccessResponse {
    private Integer code;
    private String message;

    public static SuccessResponse from(String message) {
        return SuccessResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(message)
                .build();
    }
}
