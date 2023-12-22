package com.flagship.repository;

import com.flagship.model.db.Product;
import com.flagship.model.db.Sale;
import com.flagship.model.db.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {
  Optional<Sale> findBySaleCode(String saleCode);

  Optional<Sale> findByProductAndSupplier(Product product, Supplier supplier);
}
