package com.flagship.dto.request;

import lombok.Data;

@Data
public class OrderCuttingRequest {
    private String cartonNo;
    private Double cartoonQuantity;
    private Long cartoonPiece;
    private Double sellingPrice;
}
