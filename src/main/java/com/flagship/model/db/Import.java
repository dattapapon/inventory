package com.flagship.model.db;

import lombok.*;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.db.DbConstant.DbImport;
import com.flagship.constant.enums.Category;
import com.flagship.constant.enums.Warehouse;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbImport.TABLE_NAME)
public class Import implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbImport.IMPORT_ID)
    private Long id;

    @Column(name = DbImport.SHIPMENT_NO, nullable = false)
    private String shipmentNo;

    @Column(name = DbImport.PRODUCT_ID, nullable = false)
    private String productId;

    @Column(name = DbImport.PRODUCT_NAME, nullable = false)
    private String productName;

    @Column(name = DbImport.CARTOON_QUANTITY)
    private Long cartoonQuantity;

    @Column(name = DbImport.CARTOON_BUYING_PRICE)
    private Double cartoonBuyingPrice;

    @Column(name = DbImport.CARTOON_WEIGHT)
    private Double cartoonWeight;

    @Column(name = DbImport.PIECE_QUANTITY)
    private Long pieceQuantity;

    @Column(name = DbImport.PIECE_BUYING_PRICE)
    private Double pieceBuyingPrice;

    @Column(name = DbImport.PIECE_WEIGHT)
    private Double pieceWeight;

    @Column(name = DbImport.KG_LT_QUANTITY)
    private Double kgLtQuantity;

    @Column(name = DbImport.KG_LT_BUYING_PRICE)
    private Double kgLtBuyingPrice;

    @Column(name = DbImport.BRAND, nullable = false)
    private String brand;

    @Column(name = DbImport.PRODUCTION, nullable = false)
    private ZonedDateTime production;

    @Column(name = DbImport.COUNTRY, nullable = false)
    private String country;

    @Column(name = DbImport.TAX, nullable = false)
    private Double tax;

    @Column(name = DbImport.VAT, nullable = false)
    private Double vat;

    @Column(name = DbImport.WAREHOUSE, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Warehouse warehouse;

    @Column(name = DbImport.EXPIRE, nullable = false)
    private ZonedDateTime expire;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbImport.USER_ID, referencedColumnName = DbUser.ID, nullable = false, updatable = false)
    private User receiverName;

    @Column(name = DbImport.CATEGORY, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbImport.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbImport.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbImport.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = DbImport.LAST_UPDATED_ON)
    private ZonedDateTime updatedOn;
}
