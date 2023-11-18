package com.flagship.dto.response;

import com.flagship.model.db.Cutting;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class GetCuttingResponse {
    private Integer code;
    private String cartonNo;
    private Double cartonWeight;
    private Long pieceInCarton;
    private Double cartonBuyingPrice;

    public static GetCuttingResponse from(Cutting cutting) {
        return GetCuttingResponse.builder()
                .code(HttpStatus.SC_OK)
                .cartonNo(cutting.getCartoonNo())
                .cartonWeight(cutting.getCartoonWeight())
                .pieceInCarton(cutting.getPieceInCarton())
                .cartonBuyingPrice(cutting.getCartoonBuyingPrice())
                .build();
    }
}
