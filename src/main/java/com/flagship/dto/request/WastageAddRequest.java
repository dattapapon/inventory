package com.flagship.dto.request;

import com.flagship.constant.enums.Cause;
import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class WastageAddRequest implements RequestValidator {
    @Valid
    @NotEmpty
    private String productId;
    @Valid
    @NotEmpty
    private String productName;
    @Valid
    @NotEmpty
    private String productSerialNo;
    @Valid
    @NotEmpty
    private String warHouse;
    @Valid
    @NotEmpty
    private String importDate;
    @Valid
    private Cause cause;
    @Valid
    @NotEmpty
    private String userEmail;

    @Override
    public void validate() {

    }
}
