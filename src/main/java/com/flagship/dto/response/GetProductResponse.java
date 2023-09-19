package com.flagship.dto.response;

import com.flagship.model.db.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductResponse {
    private String productId;
    private String productName;
    private String userName;

    public static GetProductResponse from(Product product) {
        return GetProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .userName(product.getCreatedBy().getName())
                .build();
    }
}
