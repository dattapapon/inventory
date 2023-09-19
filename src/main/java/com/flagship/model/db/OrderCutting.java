package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbOrderMaster;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.db.DbConstant.DbOrderCutting;
import lombok.*;
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
@Table(name = DbOrderCutting.TABLE_NAME)
public class OrderCutting implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbOrderCutting.ID)
    private Long id;

    @Column(name = DbOrderCutting.CARTON_NO, nullable = false)
    private String cartonNo;

    @Column(name = DbOrderCutting.CARTON_QUANTITY, nullable = false)
    private Double cartonQuantity;

    @Column(name = DbOrderCutting.PIECE_IN_CARTON, nullable = false)
    private Long pieceInCarton;

    @Column(name = DbOrderCutting.PRICE, nullable = false)
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderCutting.ORDER_ID, referencedColumnName = DbOrderMaster.ORDER_ID, nullable = false, updatable = false)
    private OrderMaster orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderCutting.CREATED_BY, referencedColumnName = DbUser.EMAIL, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbOrderCutting.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderCutting.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = DbOrderCutting.LAST_UPDATED_ON, nullable = false, updatable = false)
    private ZonedDateTime updatedOn;
}
