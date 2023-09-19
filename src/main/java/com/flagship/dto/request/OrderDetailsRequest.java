package com.flagship.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsRequest {
    private String productId;
    private String productName;
    private Long cartonQuantity;
    private Double cartonPrice;
    private Double cartonWeight;
    private Long pieceQuantity;
    private Double piecePrice;
    private Double pieceWeight;
    private Long kgLtQuantity;
    private Double kgLtPrice;
    private Double tax;
    private Double vat;
    private Boolean cuttings;
    List<OrderCuttingRequest> cutting;
}
