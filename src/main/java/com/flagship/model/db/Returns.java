package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbOrderMaster;
import com.flagship.constant.db.DbConstant.DbProduct;
import com.flagship.constant.db.DbConstant.DbReturn;
import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.enums.Cause;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbReturn.TABLE_NAME)
public class Returns implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DbReturn.ID)
  private Long Id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbReturn.ORDER, referencedColumnName = DbOrderMaster.ORDER_ID, nullable = false)
  private OrderMaster order;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbReturn.PRODUCT, referencedColumnName = DbProduct.PRODUCT_ID, nullable = false)
  private Product product;

  @Column(name = DbReturn.CARTOON)
  private Double cartoon;

  @Column(name = DbReturn.PIECE)
  private Double piece;

  @Column(name = DbReturn.KG_LT)
  private Double kgLt;

  @Column(name = DbReturn.DELIVERY_MAN)
  private String deliveryMan;

  @Enumerated(value = EnumType.STRING)
  @Column(name = DbReturn.CAUSE)
  private Cause cause;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = DbReturn.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
  private User createdBy;

  @CreationTimestamp
  @Column(name = DbReturn.CREATED_ON, nullable = false, updatable = false)
  private ZonedDateTime createdOn;
}
