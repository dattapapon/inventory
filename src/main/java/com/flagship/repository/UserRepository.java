package com.flagship.repository;

import com.flagship.dto.response.GetAllUserResponse;
import com.flagship.model.db.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    List<User> findAll();
}
