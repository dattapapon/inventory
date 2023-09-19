package com.flagship.dto.response;

import com.flagship.model.db.OrderCutting;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderCuttingResponse {
    private String cartonNo;
    private Double cartonQuantity;
    private Long pieceInCarton;
    private Double price;
    public static GetOrderCuttingResponse from(OrderCutting orderCutting) {
        return GetOrderCuttingResponse.builder()
                .cartonNo(orderCutting.getCartonNo())
                .cartonQuantity(orderCutting.getCartonQuantity())
                .pieceInCarton(orderCutting.getPieceInCarton())
                .price(orderCutting.getPrice())
                .build();
    }
}
