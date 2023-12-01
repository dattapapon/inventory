package com.flagship.repository;

import com.flagship.model.db.ImportDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportDetailsRepository extends PagingAndSortingRepository<ImportDetails, Long> {

}
