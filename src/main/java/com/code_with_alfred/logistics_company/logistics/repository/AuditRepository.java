package com.code_with_alfred.logistics_company.logistics.repository;

import com.code_with_alfred.logistics_company.logistics.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}