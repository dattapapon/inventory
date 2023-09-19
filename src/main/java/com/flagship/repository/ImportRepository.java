package com.flagship.repository;

import com.flagship.model.db.Cutting;
import com.flagship.model.db.Import;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportRepository extends PagingAndSortingRepository<Import, Long> {
    List<Import> findAllByProductIdOrderByCreatedOnAsc(String productId);

    List<Import> findAll();

    Optional<Import> findById(Long cuttingId);
}
