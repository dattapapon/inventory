package com.flagship.repository;

import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends PagingAndSortingRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByOrderId(OrderMaster orderId);

    List<OrderDetails> findAllByProductIdOrderByCreatedOnAsc(String productId);
}
