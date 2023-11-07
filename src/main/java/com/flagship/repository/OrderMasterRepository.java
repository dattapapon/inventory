package com.flagship.repository;

import com.flagship.model.db.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface OrderMasterRepository extends PagingAndSortingRepository<OrderMaster, Long> {
    List<OrderMaster> findAll();

    List<OrderMaster> findAllByCreatedOnBetween(ZonedDateTime start, ZonedDateTime end);
}
