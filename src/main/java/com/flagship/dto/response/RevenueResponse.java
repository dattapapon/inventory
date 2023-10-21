package com.flagship.dto.response;

import com.flagship.model.db.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevenueResponse {
    private String productId;
    private String productName;
    private Double buyingPrice;
    private Double totalBuyingPrice;
    private Double sellingPrice;
    private Double totalSellingPrice;
    private Double revenue;

    public static RevenueResponse from(Product product, Double buyingPrice, Double sellingPrice,
                                       Double totalBuyingPrice, Double totalSellingPrice, Double revenue){
        return RevenueResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .buyingPrice(buyingPrice)
                .totalBuyingPrice(totalBuyingPrice)
                .sellingPrice(sellingPrice)
                .totalSellingPrice(totalSellingPrice)
                .revenue(revenue)
                .build();

    }
}
