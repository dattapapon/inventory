package com.flagship.service;

import com.flagship.dto.request.CreateCustomerRequest;
import com.flagship.dto.response.CreateCustomerResponse;
import com.flagship.dto.response.GetAllCustomerResponse;
import com.flagship.dto.response.GetCustomerResponse;

public interface CustomerService {
    CreateCustomerResponse addCustomer(CreateCustomerRequest createCustomerRequest);

    GetAllCustomerResponse getAllCustomer();

    GetCustomerResponse getCustomer(String customerId);
}
