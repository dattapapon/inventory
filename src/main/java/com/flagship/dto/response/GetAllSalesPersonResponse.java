package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllSalesPersonResponse {
    List<GetSalesPersonResponse> getSalesPersonResponses;

    public static GetAllSalesPersonResponse from(List<GetSalesPersonResponse> getSalesPersonResponses) {
        return GetAllSalesPersonResponse.builder()
                .getSalesPersonResponses(getSalesPersonResponses)
                .build();
    }
}
