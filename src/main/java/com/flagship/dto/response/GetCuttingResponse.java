package com.flagship.dto.response;

import com.flagship.model.db.Cutting;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCuttingResponse {
    private String cartonNo;
    private Double cartonWeight;
    private Long pieceInCarton;
    private Double cartonBuyingPrice;

    public static GetCuttingResponse from(Cutting cutting) {
        return GetCuttingResponse.builder()
                .cartonNo(cutting.getCartoonNo())
                .cartonWeight(cutting.getCartoonWeight())
                .pieceInCarton(cutting.getPieceInCarton())
                .cartonBuyingPrice(cutting.getCartoonBuyingPrice())
                .build();
    }
}
