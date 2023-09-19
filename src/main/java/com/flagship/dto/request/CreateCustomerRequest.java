package com.flagship.dto.request;

import com.flagship.constant.Regex;
import com.flagship.dto.RequestValidator;
import com.flagship.exception.RequestValidationException;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.regex.Pattern;

@Data
public class CreateCustomerRequest implements RequestValidator {
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
    }
}
