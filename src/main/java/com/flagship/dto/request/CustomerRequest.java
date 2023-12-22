package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.constant.enums.CustomerType;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
public class CustomerRequest implements RequestValidator {
  @Valid
  @NotEmpty
  private String customerId;
  @Valid
  @NotEmpty
  private String customerName;
  private String company;
  @Valid
  @NotEmpty
  private String phoneNumber;
  @Valid
  private CustomerType customerType;
  private String supplier;
  private CommonRequest branch;
  @Valid
  @NotEmpty
  private String address;
  private String binNo;
  @Valid
  @NotEmpty
  private String user;

  @Override
  public void validate() {
    if (!Pattern.matches(String.valueOf(Regex.MOBILE_NUMBER_REGEX), phoneNumber)) {
      throw new RequestValidationException("Phone Number Pattern is not Match.");
    }
    if (Objects.isNull(customerType)) {
      throw new RequestValidationException("Customer type should not be null. Customer Should be LOCAL " +
              "or CORPORATE");
    }
  }
}
