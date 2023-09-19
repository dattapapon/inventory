package com.flagship.dto.response;

import com.flagship.model.db.Wastage;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;
@Data
@Builder
public class AddWastageResponse {
    private Integer code;
    private String message;
    private String productId;
    private String productName;
    private String productSerialNo;
    private String warHouse;
    private String importDate;
    private String cause;
    private String userEmail;
    public static AddWastageResponse from(String message, Wastage wastage) {
        return AddWastageResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(message)
                .productId(wastage.getProductId())
                .productName(wastage.getProductName())
                .productSerialNo(wastage.getProductSerialNo())
                .warHouse(wastage.getWarHouse())
                .importDate(wastage.getImportDate().toString())
                .cause(wastage.getCause().getName())
                .userEmail(wastage.getCreatedBy().getEmail())
                .build();
    }
}
