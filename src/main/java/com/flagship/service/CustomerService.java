package com.flagship.service;

import com.flagship.dto.request.CustomerRequest;
import com.flagship.dto.response.CustomerResponse;
import com.flagship.dto.response.GetCustomerResponse;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    GetCustomerResponse getCustomer(String customerId);
}
