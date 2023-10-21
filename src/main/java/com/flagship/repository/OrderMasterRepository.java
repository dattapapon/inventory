package com.flagship.repository;

import com.flagship.model.db.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMasterRepository extends PagingAndSortingRepository<OrderMaster, Long> {
    List<OrderMaster> findAll();
}
