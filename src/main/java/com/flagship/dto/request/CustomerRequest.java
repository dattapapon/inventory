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

    @Valid
    @NotEmpty
    private String company;

    @Valid
    @NotEmpty
    private String phoneNumber;

    @Valid
    @NotEmpty
    private String email;

    @Valid
    private CustomerType customerType;

    @Valid
    @NotEmpty
    private String userEmail;

    @Override
    public void validate() {
        if (!Pattern.matches(String.valueOf(Regex.EMAIL_REGEX), email)) {
            throw new RequestValidationException("Email Pattern is not Match.");
        }
        if (!Pattern.matches(String.valueOf(Regex.MOBILE_NUMBER_REGEX), phoneNumber)) {
            throw new RequestValidationException("Phone Number Pattern is not Match.");
        }
        if (Objects.isNull(customerType)) {
            throw new RequestValidationException("Customer type should not be null. Customer Should be LOCAL " +
                    "or CORPORATE");
        }
    }
}
