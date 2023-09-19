package com.flagship.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddCuttingRequest {
    @Valid
    @NotEmpty
    private String cartonNo;

    @Valid
    @NotNull
    private Double cartonWeight;

    @Valid
    @NotNull
    private Long pieceInCarton;

    @Valid
    @NotNull
    private Double cartonBuyingPrice;

}
