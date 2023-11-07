package com.flagship.service;

import com.flagship.dto.request.CreateSalesPersonRequest;
import com.flagship.dto.response.CreateSalesPersonResponse;
import com.flagship.dto.response.GetAllSalesPersonResponse;
import com.flagship.dto.response.GetSalesPersonResponse;

public interface SalesPersonService {
    CreateSalesPersonResponse addSalesPerson(CreateSalesPersonRequest createCustomerRequest);

    GetAllSalesPersonResponse getAllSalesPerson();

    GetSalesPersonResponse getSalesPerson(String salesPersonId);
}
