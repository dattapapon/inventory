package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class AllWastageResponse {
  private Integer code;
  private List<SingleWastageResponse> wastageResponse;
  public static AllWastageResponse from(List<SingleWastageResponse> wastageResponse){
    return AllWastageResponse.builder()
            .code(HttpStatus.SC_OK)
            .wastageResponse(wastageResponse)
            .build();
  }
}
