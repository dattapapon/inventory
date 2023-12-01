package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetAllImportResponse {
    private Integer code;
    private List<ImportResponse> importResponseList;

    public static GetAllImportResponse from(List<ImportResponse> importResponses) {
        return GetAllImportResponse.builder()
                .code(HttpStatus.SC_OK)
                .importResponseList(importResponses)
                .build();
    }
}
