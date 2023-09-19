package com.flagship.dto.response;

import com.flagship.constant.enums.Category;
import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Import;
import com.flagship.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImportResponse {
    private Long importId;
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
    private String country;
    private Double tax;
    private Double vat;
    private Warehouse warehouse;
    private String expire;
    private String receiverName;
    private Category category;
    private String name;

    public static GetImportResponse from(Import imports){
        return GetImportResponse.builder()
                .importId(imports.getId())
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
                .production(DateUtil.getFormattedDate(imports.getProduction()))
                .productName(imports.getProductName())
                .country(imports.getCountry())
                .tax(imports.getTax())
                .vat(imports.getVat())
                .warehouse(imports.getWarehouse())
                .expire(DateUtil.getFormattedDate(imports.getExpire()))
                .receiverName(imports.getReceiverName().getName())
                .category(imports.getCategory())
                .name(imports.getCreatedBy().getName())
                .build();

    }
}
