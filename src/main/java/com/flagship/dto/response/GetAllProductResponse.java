package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllProductResponse {
    List<GetProductResponse> getAllProductResponse;
    public static GetAllProductResponse from(List<GetProductResponse> allProduct) {
        return GetAllProductResponse.builder()
                .getAllProductResponse(allProduct)
                .build();
    }
}
