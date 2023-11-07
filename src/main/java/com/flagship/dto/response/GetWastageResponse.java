package com.flagship.dto.response;

import com.flagship.model.db.Wastage;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetWastageResponse {
    private String productId;
    private String productName;
    private String productSerialNo;
    private String importDate;
    private String cause;

    public static GetWastageResponse from(Wastage wastage) {
        return GetWastageResponse.builder()
                .productId(wastage.getProductId())
                .productName(wastage.getProductName())
                .productSerialNo(wastage.getProductSerialNo())
                .importDate(DateUtil.getFormattedDate(wastage.getImportDate()))
                .cause(wastage.getCause().getName())
                .build();
    }
}
