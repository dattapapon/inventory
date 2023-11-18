package com.flagship.dto.response;

import com.flagship.model.db.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RevenueResponse {
    private String productId;
    private String productName;
    private double buyingPrice;
    private double totalBuyingPrice;
    private double sellingPrice;
    private double totalSellingPrice;
    private double revenue;

    public static RevenueResponse from(Product product, double buyingPrice, double sellingPrice,
                                       double totalBuyingPrice, double totalSellingPrice, double revenue) {
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
