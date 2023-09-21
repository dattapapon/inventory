package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;
import com.flagship.constant.enums.Category;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Import;

import java.util.List;

@Data
@Builder
public class AddImportResponse {
    private Integer code;
    private String message;
    private String shipmentNo;
    private String productId;
    private String productName;
    private Long cartoonQuantity;
    private Double cartoonBuyingPrice;
    private Double cartoonWeight;
    private Long pieceQuantity;
    private Double pieceBuyingPrice;
    private Double pieceWeight;
    private Double kgLtQuantity;
    private Double kgLtBuyingPrice;
    private String brand;
    private String production;
    private String productionName;
    private String country;
    private Double tax;
    private Double vat;
    private Warehouse warehouse;
    private String expire;
    private String receiverName;
    private Category category;
    private String name;
    List<GetCuttingResponse> getCuttingResponse;

    public static AddImportResponse from(String message, Import imports, List<GetCuttingResponse> getCuttingResponse){
        return AddImportResponse.builder()
                .code(HttpStatus.SC_OK)
                .message(message)
                .shipmentNo(imports.getShipmentNo())
                .productId(imports.getProductId())
                .productName(imports.getProductName())
                .cartoonQuantity(imports.getCartoonQuantity())
                .cartoonBuyingPrice(imports.getCartoonBuyingPrice())
                .cartoonWeight(imports.getCartoonWeight())
                .pieceQuantity(imports.getPieceQuantity())
                .pieceBuyingPrice(imports.getPieceBuyingPrice())
                .pieceWeight(imports.getPieceWeight())
                .kgLtQuantity(imports.getKgLtQuantity())
                .kgLtBuyingPrice(imports.getKgLtBuyingPrice())
                .brand(imports.getBrand())
                .production(imports.getProduction().toString())
                .productName(imports.getProductName())
                .country(imports.getCountry())
                .tax(imports.getTax())
                .vat(imports.getVat())
                .warehouse(imports.getWarehouse())
                .expire(imports.getExpire().toString())
                .receiverName(imports.getReceiverName().getName())
                .category(imports.getCategory())
                .name(imports.getCreatedBy().getName())
                .getCuttingResponse(getCuttingResponse)
                .build();

    }
}