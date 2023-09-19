package com.flagship.repository;

import com.flagship.model.db.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> findByProductId(String productId);
    List<Product> findAll();
}
