package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AddOrderMasterResponse {
    List<GetOrderDetailsResponse> orders;
    private Integer code;
    private String message;
    private String salesPersonName;
    private String supplierCode;
    private String customerName;
    private String phoneNumber;
    private String customerStatus;
    private String customerType;
    private String companyName;
    private String orderDate;
    private String creditTerm;
    private String orderId;

    public static AddOrderMasterResponse from(String message, OrderMaster orderMaster, List<GetOrderDetailsResponse> getOrderDetailsResponses) {
        return AddOrderMasterResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(message)
                .salesPersonName(orderMaster.getSalesPersonName())
                .supplierCode(orderMaster.getSupplierCode())
                .customerName(orderMaster.getCustomerName())
                .phoneNumber(orderMaster.getPhoneNumber())
                .customerStatus(orderMaster.getCustomerStatus())
                .customerType(orderMaster.getCustomerType())
                .companyName(orderMaster.getCompanyName())
                .orderDate(orderMaster.getOrderDate().toString())
                .creditTerm(orderMaster.getCreditTerm().toString())
                .orderId(orderMaster.getOrderId())
                .orders(getOrderDetailsResponses)
                .build();
    }
}
