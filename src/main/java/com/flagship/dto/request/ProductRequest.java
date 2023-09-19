package com.flagship.dto.request;

import lombok.Data;
import com.flagship.dto.RequestValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
@Data
public class ProductRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String productId;
    @Valid
    @NotEmpty
    private String productName;
    @Valid
    @NotEmpty
    private String userEmail;

    @Override
    public void validate() {

    }
}
