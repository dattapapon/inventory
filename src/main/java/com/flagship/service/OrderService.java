package com.flagship.service;

import com.flagship.dto.request.OrderMasterRequest;
import com.flagship.dto.response.AddOrderMasterResponse;

public interface OrderService {
    AddOrderMasterResponse createOrder(OrderMasterRequest orderMasterRequest);
}
