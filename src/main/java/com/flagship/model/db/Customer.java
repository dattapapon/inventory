package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbCustomer;
import com.flagship.constant.db.DbConstant.DbUser;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbCustomer.TABLE_NAME)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbCustomer.ID)
    private Long id;

    @Column(name = DbCustomer.CUSTOMER_ID, nullable = false, unique = true)
    private String customerId;

    @Column(name = DbCustomer.CUSTOMER_NAME, nullable = false)
    private String customerName;

    @Column(name = DbCustomer.COMPANY, nullable = false)
    private String company;

    @Column(name = DbCustomer.PHONE_NUMBER, nullable = false)
    private String phoneNumber;

    @Column(name = DbCustomer.EMAIL, nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCustomer.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbUser.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCustomer.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = DbCustomer.LAST_UPDATED_ON, nullable = false, updatable = false)
    private ZonedDateTime updatedOn;
}
