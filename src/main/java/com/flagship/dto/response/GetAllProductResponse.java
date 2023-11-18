package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetAllProductResponse {
    private List<GetProductResponse> getAllProductResponse;
    private Integer code;
    public static GetAllProductResponse from(List<GetProductResponse> allProduct) {
        return GetAllProductResponse.builder()
                .code(HttpStatus.SC_OK)
                .getAllProductResponse(allProduct)
                .build();
    }
}
