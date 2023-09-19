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
@Table(name = DbOrderMaster.TABLE_NAME)
public class OrderMaster implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbOrderMaster.ID)
    private Long id;

    @Column(name = DbOrderMaster.SALES_PERSON_NAME, nullable = false)
    private String salesPersonName;

    @Column(name = DbOrderMaster.SUPPLIER_CODE, nullable = false)
    private String supplierCode;

    @Column(name = DbOrderMaster.CUSTOMER_NAME, nullable = false)
    private String customerName;

    @Column(name = DbOrderMaster.ORDER_ID, nullable = false)
    private String orderId;

    @Column(name = DbOrderMaster.PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = DbOrderMaster.CUSTOMER_STATUS)
    private String customerStatus;

    @Column(name = DbOrderMaster.CUSTOMER_TYPE)
    private String customerType;

    @Column(name = DbOrderMaster.COMPANY_NAME)
    private String companyName;

    @Column(name = DbOrderMaster.ORDER_DATE)
    private ZonedDateTime orderDate;

    @Column(name = DbOrderMaster.CREDIT_TERM)
    private ZonedDateTime creditTerm;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbOrderMaster.CREATED_BY, referencedColumnName = DbUser.EMAIL, updatable = false)
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
