package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbOrderMaster;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.db.DbConstant.DbOrderDetails;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbOrderDetails.TABLE_NAME)
public class OrderDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbOrderDetails.ID)
    private Long id;

    @Column(name = DbOrderDetails.PRODUCT_ID, nullable = false)
    private String productId;

    @Column(name = DbOrderDetails.PRODUCT_NAME, nullable = false)
    private String productName;

    @Column(name = DbOrderDetails.CARTON_QUANTITY, nullable = false)
    private Long cartonQuantity;

    @Column(name = DbOrderDetails.CARTOON_SELLING_PRICE)
    private Double cartonSellingPrice;

    @Column(name = DbOrderDetails.CARTOON_WEIGHT)
    private Double cartonWeight;

    @Column(name = DbOrderDetails.PIECE_QUANTITY)
    private Long pieceQuantity;

    @Column(name = DbOrderDetails.PIECE_SELLING_PRICE)
    private Double pieceSellingPrice;

    @Column(name = DbOrderDetails.PIECE_WEIGHT)
    private Double pieceWeight;

    @Column(name = DbOrderDetails.KG_LT_QUANTITY)
    private Long kgLtQuantity;

    @Column(name = DbOrderDetails.KG_LT_SELLING_PRICE)
    private Double kgLtSellingPrice;

    @Column(name = DbOrderDetails.TAX)
    private Double tax;

    @Column(name = DbOrderDetails.VAT)
    private Double vat;

    @Column(name = DbOrderDetails.TOTAL_BILL)
    private Double bill;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderDetails.ORDER_ID, referencedColumnName = DbOrderMaster.ORDER_ID, nullable = false, updatable = false)
    private OrderMaster orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderDetails.CREATED_BY, referencedColumnName = DbUser.EMAIL, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbOrderDetails.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderDetails.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = DbOrderDetails.LAST_UPDATED_ON, nullable = false, updatable = false)
    private ZonedDateTime updatedOn;
}
