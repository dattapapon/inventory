package com.flagship.service;

import com.flagship.dto.request.OrderMasterRequest;
import com.flagship.dto.response.AddOrderMasterResponse;
import com.flagship.dto.response.OrderBillsResponse;

import java.util.List;

public interface OrderService {
    AddOrderMasterResponse createOrder(OrderMasterRequest orderMasterRequest);

    List<OrderBillsResponse> getAllBills();

    List<OrderBillsResponse> getAllBillsByTime(String start, String end);
}
