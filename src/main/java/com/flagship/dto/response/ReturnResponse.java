package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.model.db.Returns;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class ReturnResponse {
  private Integer code;
  private String message;
  private String product;
  private Long order;
  private Double cartoon;
  private Double piece;
  private Double kgLt;
  private String deliveryMan;
  private Cause cause;

  public static ReturnResponse from(String message, Returns returns) {
    return ReturnResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .product(returns.getProduct().getProductName())
            .order(returns.getOrder().getOrderId())
            .cartoon(returns.getCartoon())
            .piece(returns.getPiece())
            .kgLt(returns.getKgLt())
            .deliveryMan(returns.getDeliveryMan())
            .cause(returns.getCause())
            .build();
  }
}
