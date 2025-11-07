package com.code_with_alfred.logistics_company.logistics.repository;


import com.code_with_alfred.logistics_company.logistics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = 'DRIVER' AND u.enabled = true")
    List<User> findAllActiveDrivers();

    List<User> findByRole(com.code_with_alfred.logistics_company.logistics.entity.Role role);
}