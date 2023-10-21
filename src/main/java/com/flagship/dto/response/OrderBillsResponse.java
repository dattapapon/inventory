package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderBillsResponse {
    private String orderId;
    private String customerName;
    private String customerStatus;
    private String customerType;
    private String phoneNumber;
    private String salesPerson;
    private String companyName;
    private Double bills;

    public static OrderBillsResponse from(OrderMaster orderMaster, Double bills){
        return OrderBillsResponse.builder()
                .orderId(orderMaster.getOrderId())
                .customerName(orderMaster.getCustomerName())
                .customerStatus(orderMaster.getCustomerStatus())
                .customerType(orderMaster.getCustomerType())
                .phoneNumber(orderMaster.getPhoneNumber())
                .salesPerson(orderMaster.getSalesPersonName())
                .companyName(orderMaster.getCompanyName())
                .bills(bills)
                .build();

    }
}
