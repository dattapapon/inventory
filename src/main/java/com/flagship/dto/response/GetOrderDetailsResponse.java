package com.flagship.dto.response;

import com.flagship.dto.request.OrderCuttingRequest;
import com.flagship.model.db.OrderDetails;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class GetOrderDetailsResponse {
    private String productId;
    private String productName;
    private Long cartonQuantity;
    private Double cartonSellingPrice;
    private Double cartonWeight;
    private Long pieceQuantity;
    private Double pieceSellingPrice;
    private Double pieceWeight;
    private Long kgLtQuantity;
    private Double kgLtSellingPrice;
    private Double tax;
    private Double vat;
    private Double bill;
    List<GetOrderCuttingResponse> cutting;
    public static GetOrderDetailsResponse from(OrderDetails orderDetails, List<GetOrderCuttingResponse> cuttingResponses) {
        return GetOrderDetailsResponse.builder()
                .productId(orderDetails.getProductId())
                .productName(orderDetails.getProductName())
                .cartonQuantity(orderDetails.getCartonQuantity())
                .cartonSellingPrice(orderDetails.getPieceSellingPrice())
                .cartonWeight(orderDetails.getCartonWeight())
                .pieceQuantity(orderDetails.getPieceQuantity())
                .pieceSellingPrice(orderDetails.getPieceSellingPrice())
                .pieceWeight(orderDetails.getPieceWeight())
                .kgLtQuantity(orderDetails.getKgLtQuantity())
                .kgLtSellingPrice(orderDetails.getKgLtSellingPrice())
                .tax(orderDetails.getTax())
                .vat(orderDetails.getVat())
                .bill(orderDetails.getBill())
                .cutting(cuttingResponses)
                .build();
    }
}
