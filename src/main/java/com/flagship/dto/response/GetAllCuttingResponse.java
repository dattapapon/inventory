package com.flagship.dto.response;

import com.flagship.model.db.Cutting;
import com.flagship.model.db.Import;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class GetAllCuttingResponse {
    private Integer code;
    private Long importId;
    private String productId;
    private String productName;
    private String cartonNo;
    private Double cartonWeight;
    private Long cartonPiece;

    public static GetAllCuttingResponse from(Cutting cutting, Import imports) {
        return GetAllCuttingResponse.builder()
                .code(HttpStatus.SC_OK)
                .importId(cutting.getImportId().getId())
                .productId(imports.getProductId())
                .productName(imports.getProductName())
                .cartonNo(cutting.getCartoonNo())
                .cartonWeight(cutting.getCartoonWeight())
                .cartonPiece(cutting.getPieceInCarton())
                .build();
    }
}
