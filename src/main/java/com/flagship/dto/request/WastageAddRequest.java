package com.flagship.dto.request;

import com.flagship.constant.enums.Cause;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class WastageAddRequest implements RequestValidator {
  @Valid
  @NotNull
  private CommonRequest product;
  @Valid
  @NotNull
  private CommonRequest shipment;
  private Double cartoon;
  private Double piece;
  private Double kgLt;
  @Valid
  private Cause cause;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {
    if (Objects.isNull(cause)) {
      throw new RequestValidationException("Cause should not be null");
    }
  }
}
