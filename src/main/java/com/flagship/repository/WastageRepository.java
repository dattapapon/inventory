package com.flagship.repository;

import com.flagship.model.db.Wastage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface WastageRepository extends PagingAndSortingRepository<Wastage, Long> {
    List<Wastage> findAll();

    List<Wastage> findByCreatedOnBetweenOrderByCreatedOnAsc(ZonedDateTime start, ZonedDateTime end);
}
