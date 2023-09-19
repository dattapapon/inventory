package com.flagship.dto.response;

import com.flagship.model.db.SalesPerson;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class CreateSalesPersonResponse {
    private Integer code;
    private String message;
    private String salesPersonId;
    private String salesPersonName;
    private String phoneNumber;
    private String area;

    public static CreateSalesPersonResponse from(String message, SalesPerson salesPerson) {
        return CreateSalesPersonResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(message)
                .salesPersonId(salesPerson.getSalesPersonId())
                .salesPersonName(salesPerson.getSalesPersonName())
                .phoneNumber(salesPerson.getPhoneNumber())
                .area(salesPerson.getArea())
                .build();
    }
}