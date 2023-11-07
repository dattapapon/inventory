package com.flagship.model.db;

import com.flagship.constant.db.DbConstant.DbCutting;
import com.flagship.constant.db.DbConstant.DbImport;
import com.flagship.constant.db.DbConstant.DbUser;
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
@Table(name = DbCutting.TABLE_NAME)
public class Cutting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbCutting.ID)
    private Long id;

    @Column(name = DbCutting.CARTOON_NO, nullable = false)
    private String cartoonNo;

    @Column(name = DbCutting.CARTOON_WEIGHT, nullable = false)
    private Double cartoonWeight;

    @Column(name = DbCutting.PIECE_IN_CARTOON, nullable = false)
    private Long pieceInCarton;

    @Column(name = DbCutting.CARTOON_BUYING_PRICE, nullable = false)
    private Double cartoonBuyingPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCutting.IMPORT_ID, referencedColumnName = DbImport.IMPORT_ID, nullable = false, updatable = false)
    private Import importId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCutting.CREATED_BY, referencedColumnName = DbUser.EMAIL, nullable = false, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbCutting.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCutting.LAST_UPDATED_BY, referencedColumnName = DbUser.EMAIL)
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = DbCutting.LAST_UPDATED_ON)
    private ZonedDateTime updatedOn;
}
