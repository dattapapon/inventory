package com.flagship.repository;

import com.flagship.model.db.OrderDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends PagingAndSortingRepository<OrderDetails, Long> {
}
