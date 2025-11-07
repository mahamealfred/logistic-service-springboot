package com.code_with_alfred.logistics_company.logistics.repository;



import com.code_with_alfred.logistics_company.logistics.entity.Shipment;
import com.code_with_alfred.logistics_company.logistics.entity.ShipmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    List<Shipment> findByStatus(ShipmentStatus status);

    List<Shipment> findByAssignedDriverId(Long driverId);

    Page<Shipment> findAll(Pageable pageable);

    @Query("SELECT s FROM Shipment s WHERE s.estimatedDeliveryDate BETWEEN :start AND :end")
    List<Shipment> findShipmentsDueForDelivery(@Param("start") LocalDateTime start,
                                               @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(s) FROM Shipment s WHERE s.status = :status")
    Long countByStatus(@Param("status") ShipmentStatus status);

    @Query("SELECT s FROM Shipment s WHERE s.createdAt >= :startDate AND s.createdAt <= :endDate")
    List<Shipment> findShipmentsInDateRange(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);
}