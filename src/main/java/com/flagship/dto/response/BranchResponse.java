package com.flagship.dto.response;

import com.flagship.model.db.Branch;
import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
public class BranchResponse {
  private Integer code;
  private String message;
  private String supplier;
  private String branchCode;
  private String branchName;

  public static BranchResponse from(String message, Branch branch) {
    return BranchResponse.builder()
            .code(HttpStatus.SC_OK)
            .message(message)
            .supplier(branch.getSupplier().getSupplierName())
            .branchCode(branch.getBranchCode())
            .branchName(branch.getBranchName())
            .build();
  }
}
