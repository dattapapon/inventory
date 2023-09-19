package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbUser;
import com.flagship.constant.db.DbConstant.DbWastage;
import com.flagship.constant.enums.Cause;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbWastage.TABLE_NAME)
public class Wastage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbWastage.ID)
    private Long id;

    @Column(name = DbWastage.PRODUCT_ID, nullable = false)
    private String productId;

    @Column(name = DbWastage.PRODUCT_NAME, nullable = false, unique = true)
    private String productName;

    @Column(name = DbWastage.PRODUCT_SERIAL_NO, nullable = false)
    private String productSerialNo;

    @Column(name = DbWastage.WAR_HOUSE, nullable = false)
    private String warHouse;

    @Column(name = DbWastage.IMPORT_DATE, nullable = false)
    private ZonedDateTime importDate;

    @Column(name = DbWastage.CAUSE, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Cause cause;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbWastage.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbUser.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbWastage.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
    private User updatedBy;

    @CreationTimestamp
    @Column(name = DbWastage.LAST_UPDATED_ON, nullable = false, updatable = false)
    private ZonedDateTime updatedOn;
}
