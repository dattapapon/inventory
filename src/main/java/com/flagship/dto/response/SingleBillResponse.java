package com.flagship.dto.response;

import com.flagship.model.db.OrderMaster;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleBillResponse {
  private Long orderId;
  private String customerId;
  private String customer;
  private String supplier;
  private String orderDate;
  private String company;
  private String address;
  private String delivery;
  private String bin;
  private Integer challan;

  public static SingleBillResponse from(OrderMaster orderMaster) {
    SingleBillResponse.SingleBillResponseBuilder builder = SingleBillResponse.builder()
            .customerId(orderMaster.getCustomer().getCustomerId())
            .customer(orderMaster.getCustomer().getCustomerName())
            .orderId(orderMaster.getOrderId())
            .orderDate(DateUtil.getFormattedDate(orderMaster.getOrderDate()))
            .company(orderMaster.getCompanyName())
            .address(orderMaster.getCustomer().getAddress())
            .delivery(orderMaster.getAddress())
            .bin(orderMaster.getCustomer().getBinNo())
            .challan(orderMaster.getChallan());
    if (orderMaster.getSupplier() != null) {
      builder.supplier(orderMaster.getSupplier().getSupplierId());
    } else {
      builder.supplier(null);
    }
    return builder.build();
  }
}
