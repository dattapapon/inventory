package com.flagship.dto.response;

import com.flagship.constant.enums.UOM;
import com.flagship.model.db.OrderDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleVatDetailsResponse {
  private String product;
  private UOM uom;
  private Double quantity;
  private Double price;
  private Double total;
  private Double tax;
  private Double taxAmount;
  private String vat;
  private Double vatAmount;
  private Double totalAmountWithVatTax;

  public static SingleVatDetailsResponse from(OrderDetails orderDetails) {
    SingleVatDetailsResponse.SingleVatDetailsResponseBuilder builder = SingleVatDetailsResponse.builder()
            .product(orderDetails.getProduct().getProductName())
            .uom(orderDetails.getUom())
            .quantity(orderDetails.getQuantity())
            .price(orderDetails.getPrice())
            .total(orderDetails.getTotalPrice());
    Double totalVatAmount;
    if (orderDetails.getVat() != null) {
      builder.vat(orderDetails.getVat() + "%");

      totalVatAmount = (((orderDetails.getVat() / 100.0)) * orderDetails.getPrice()) * orderDetails.getQuantity();
      builder.vatAmount(totalVatAmount);
      builder.totalAmountWithVatTax(orderDetails.getTotalPrice() + totalVatAmount);
    } else {
      builder.vat("0.00%");
      builder.vatAmount(null);
      builder.totalAmountWithVatTax(orderDetails.getTotalPrice());
    }
    return builder.build();
  }
}
