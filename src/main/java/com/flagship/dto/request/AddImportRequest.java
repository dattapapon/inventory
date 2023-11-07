package com.flagship.dto.request;

import com.flagship.constant.enums.Category;
import com.flagship.constant.enums.Warehouse;
import com.flagship.dto.RequestValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddImportRequest implements RequestValidator {
    List<AddCuttingRequest> cartons;
    @Valid
    @NotEmpty
    private String shipmentNo;
    @Valid
    @NotEmpty
    private String productId;
    @Valid
    @NotEmpty
    private String productName;
    private Long cartoonQuantity;
    private Double cartoonBuyingPrice;
    private Double cartoonWeight;
    private Long pieceQuantity;
    private Double pieceBuyingPrice;
    private Double pieceWeight;
    private Double kgLtQuantity;
    private Double kgLtBuyingPrice;
    @Valid
    @NotEmpty
    private String brand;
    @Valid
    @NotEmpty
    private String production;
    @Valid
    @NotEmpty
    private String country;
    @Valid
    @NotNull
    private Double tax;
    @Valid
    @NotNull
    private Double vat;
    @Valid
    private Warehouse warehouse;
    @Valid
    @NotEmpty
    private String expire;
    @Valid
    @NotNull
    private Long receiverName;
    @Valid
    private Category category;
    @Valid
    @NotEmpty
    private String userEmail;

    @Override
    public void validate() {

    }
}
