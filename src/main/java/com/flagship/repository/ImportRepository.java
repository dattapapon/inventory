package com.flagship.repository;

import com.flagship.constant.enums.Warehouse;
import com.flagship.model.db.Import;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImportRepository extends PagingAndSortingRepository<Import, Long> {
    List<Import> findAllByProductIdOrderByCreatedOnAsc(String productId);

    List<Import> findAll();

    Optional<Import> findById(Long importId);

    List<Import> findAllByWarehouseAndCreatedOnBetween(Warehouse warehouse, ZonedDateTime start, ZonedDateTime end);

    List<Import> findAllByProductIdAndCreatedOnBetween(String productId, ZonedDateTime start, ZonedDateTime end);

    List<Import> findAllByProductIdAndWarehouseAndCreatedOnBetween(String productId, Warehouse warehouse, ZonedDateTime start, ZonedDateTime end);

    List<Import> findAllByProductIdAndWarehouse(String productId, Warehouse warehouse);

    List<Import> findAllByCreatedOnBetween(ZonedDateTime start, ZonedDateTime end);
}
