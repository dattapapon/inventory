package com.flagship.repository;

import com.flagship.constant.enums.OrderStatus;
import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import com.flagship.model.db.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends PagingAndSortingRepository<OrderDetails, Long> {
  List<OrderDetails> findAllByOrder(OrderMaster orderId);

  List<OrderDetails> findByProduct(Product product);

  List<OrderDetails> findByOrderAndStatus(OrderMaster orderMaster, OrderStatus orderStatus);

  Optional<OrderDetails> findByOrderAndProduct(OrderMaster order, Product product);
}
