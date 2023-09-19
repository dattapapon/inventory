package com.flagship.repository;

import com.flagship.model.db.OrderCutting;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCuttingRepository extends PagingAndSortingRepository<OrderCutting, Long> {
}
