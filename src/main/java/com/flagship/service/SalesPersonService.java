package com.flagship.service;

import com.flagship.dto.request.CreateSalesPersonRequest;
import com.flagship.dto.response.*;

public interface SalesPersonService {
    CreateSalesPersonResponse addSalesPerson(CreateSalesPersonRequest createCustomerRequest);

    GetAllSalesPersonResponse getAllSalesPerson();

    GetSalesPersonResponse getSalesPerson(String salesPersonId);
}
