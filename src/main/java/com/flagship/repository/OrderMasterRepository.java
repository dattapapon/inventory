package com.flagship.repository;

import com.flagship.model.db.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterRepository extends PagingAndSortingRepository<OrderMaster, Long> {
}
