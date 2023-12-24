package com.flagship.repository;

import com.flagship.model.db.ImportDetails;
import com.flagship.model.db.ImportMaster;
import com.flagship.model.db.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportDetailsRepository extends PagingAndSortingRepository<ImportDetails, Long> {

  List<ImportDetails> findByProduct(Product products);

  Optional<ImportDetails> findByProductAndImportMaster(Product product, ImportMaster importMaster);

  List<ImportDetails> findByImportMaster(ImportMaster importMaster);
}
