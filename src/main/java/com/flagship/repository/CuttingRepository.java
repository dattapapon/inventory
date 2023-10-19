package com.flagship.repository;

import com.flagship.model.db.Cutting;
import com.flagship.model.db.Import;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuttingRepository extends PagingAndSortingRepository<Cutting, Long> {
    List<Cutting> findAllByImportIdOrderByCreatedOnDesc(Import cuttingId);
    List<Cutting> findAll();
}
