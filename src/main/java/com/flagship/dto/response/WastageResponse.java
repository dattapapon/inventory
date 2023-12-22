package com.flagship.dto.response;

import com.flagship.constant.enums.Cause;
import com.flagship.model.db.Wastage;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class WastageResponse {
  private Integer code;
  private String message;
  private String product;
  private String shipment;
  private Double cartoon;
  private Double piece;
  private Double kgLt;
  private Cause cause;
  private String user;

  public static WastageResponse from(String message, Wastage wastage) {
    return WastageResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .product(wastage.getProduct().getProductName())
            .shipment(wastage.getShipment().getShipmentNo())
            .cartoon(wastage.getCartoon())
            .piece(wastage.getPiece())
            .kgLt(wastage.getKgLt())
            .cause(wastage.getCause())
            .user(wastage.getCreatedBy().getEmail())
            .build();
  }
}
