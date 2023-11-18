package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetAllImportResponse {
    private Integer code;
    private List<GetImportResponse> getImportResponseList;

    public static GetAllImportResponse from(List<GetImportResponse> importResponses) {
        return GetAllImportResponse.builder()
                .code(HttpStatus.SC_OK)
                .getImportResponseList(importResponses)
                .build();
    }
}
