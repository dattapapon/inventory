package com.flagship.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderMasterRequest {
    private String salesPersonName;
    private String supplierCode;
    private String customerName;
    private String phoneNumber;
    private String customerType;
    private String customerStatus;
    private String companyName;
    private String orderId;
    private String orderDate;
    private String creditTerm;
    List<OrderDetailsRequest> orders;
}
