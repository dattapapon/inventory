package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllImportResponse {
    List<GetImportResponse> getImportResponseList;

    public static GetAllImportResponse from(List<GetImportResponse> importResponses){
        return GetAllImportResponse.builder()
                .getImportResponseList(importResponses)
                .build();
    }
}
