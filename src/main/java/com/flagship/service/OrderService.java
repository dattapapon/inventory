package com.flagship.service;

import com.flagship.dto.request.EditOrderRequest;
import com.flagship.dto.request.OrderMasterRequest;
import com.flagship.dto.request.UpdateOrderRequest;
import com.flagship.dto.response.*;

import java.util.List;

public interface OrderService {
  OrderResponse createOrder(OrderMasterRequest orderMasterRequest);

  ChallanResponse getCustomerChallan(String customer);

  UomAndAvailableResponse getUomAndAvailable(String product, String shipment);

  AllBillsResponse getBills();

  BillsDetailsResponse getBillsDetails(Long orderId);

  VatDetailsResponse getVatDetails(Long orderId);

  LastPriceResponse getProductLastPrice(String customer, String product);

  DailyOrderResponse getDailyOrder(String date, String type);

  DailyOrderShortResponse getDailyOrderShort(String date, String type);

  OrderTableResponse getTable();

  EditOrderResponse editOrder(Long order, List<EditOrderRequest> updateOrderRequest);

  ReturnDetailsResponse getReturn(Long orderId);

  AllPendingOrdersResponse getPendingOrder();

  OrderRequisitionResponse updateOrderStatus(List<UpdateOrderRequest> updateOrderRequest);
}
