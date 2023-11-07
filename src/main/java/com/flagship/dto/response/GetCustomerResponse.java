package com.flagship.dto.response;

import com.flagship.model.db.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCustomerResponse {
    private String customerId;
    private String customerName;
    private String company;
    private String phoneNumber;
    private String email;

    public static GetCustomerResponse from(Customer customer) {
        return GetCustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .company(customer.getCompany())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .build();
    }
}
