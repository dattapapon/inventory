package com.flagship.dto.response;

import com.flagship.model.db.Customer;
import com.flagship.model.db.SalesPerson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSalesPersonResponse {
    private String salesPersonId;
    private String salesPersonName;
    private String phoneNumber;
    private String area;

    public static GetSalesPersonResponse from(SalesPerson salesPerson){
        return GetSalesPersonResponse.builder()
                .salesPersonId(salesPerson.getSalesPersonId())
                .salesPersonName(salesPerson.getSalesPersonName())
                .phoneNumber(salesPerson.getPhoneNumber())
                .area(salesPerson.getArea())
                .build();
    }
}
