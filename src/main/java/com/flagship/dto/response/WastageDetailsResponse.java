package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class WastageDetailsResponse {
  private Integer code;
  private String name;
  private String date;
  private Double piece;
  private String cartoonKgLt;
  private List<SingleWastageSlipResponse> wastageResponses;

  public static WastageDetailsResponse from(Double piece, Double cartoon, Double kgLt,
                                           List<SingleWastageSlipResponse> wastageResponses) {
    return WastageDetailsResponse.builder()
            .code(HttpStatus.SC_OK)
            .piece(piece)
            .cartoonKgLt(cartoon + " ctn: " + kgLt + " kg")
            .wastageResponses(wastageResponses)
            .build();
  }
}
