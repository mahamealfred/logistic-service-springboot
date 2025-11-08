package com.code_with_alfred.logistics_company.logistics.service;


import com.code_with_alfred.logistics_company.logistics.dto.request.ShipmentCreateRequest;
import com.code_with_alfred.logistics_company.logistics.dto.request.ShipmentUpdateRequest;
import com.code_with_alfred.logistics_company.logistics.entity.Shipment;
import com.code_with_alfred.logistics_company.logistics.entity.ShipmentStatus;
import com.code_with_alfred.logistics_company.logistics.entity.User;
import com.code_with_alfred.logistics_company.logistics.repository.ShipmentRepository;
import com.code_with_alfred.logistics_company.logistics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Shipment createShipment(ShipmentCreateRequest request) {
        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        User driver = null;
        if (request.getAssignedDriverId() != null) {
            driver = userRepository.findById(request.getAssignedDriverId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
        }

        Shipment shipment = Shipment.builder()
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .status(ShipmentStatus.PENDING)
                .type(request.getType())
                .weight(request.getWeight())
                .volume(request.getVolume())
                .declaredValue(request.getDeclaredValue())
                .sender(sender)
                .receiver(receiver)
                .assignedDriver(driver)
                .pickupDate(request.getPickupDate())
                .estimatedDeliveryDate(request.getEstimatedDeliveryDate())
                .build();

        return shipmentRepository.save(shipment);
    }

    @Cacheable(value = "shipments", key = "#id")
    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + id));
    }

    @Cacheable(value = "shipments", key = "#trackingNumber")
    public Shipment getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Shipment not found with tracking number: " + trackingNumber));
    }

    public Page<Shipment> getAllShipments(Pageable pageable) {
        return shipmentRepository.findAll(pageable);
    }

    public List<Shipment> getShipmentsByStatus(ShipmentStatus status) {
        return shipmentRepository.findByStatus(status);
    }

    @Transactional
    @CacheEvict(value = "shipments", key = "#id")
    public Shipment updateShipmentStatus(Long id, ShipmentStatus status) {
        Shipment shipment = getShipmentById(id);
        shipment.setStatus(status);

        if (status == ShipmentStatus.DELIVERED) {
            shipment.setActualDeliveryDate(LocalDateTime.now());
        }

        return shipmentRepository.save(shipment);
    }

    @Transactional
    @CacheEvict(value = "shipments", key = "#id")
    public Shipment updateShipment(Long id, ShipmentUpdateRequest request) {
        Shipment shipment = getShipmentById(id);

        if (request.getAssignedDriverId() != null) {
            User driver = userRepository.findById(request.getAssignedDriverId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            shipment.setAssignedDriver(driver);
        }

        if (request.getStatus() != null) {
            shipment.setStatus(request.getStatus());
            if (request.getStatus() == ShipmentStatus.DELIVERED) {
                shipment.setActualDeliveryDate(LocalDateTime.now());
            }
        }

        if (request.getEstimatedDeliveryDate() != null) {
            shipment.setEstimatedDeliveryDate(request.getEstimatedDeliveryDate());
        }

        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getDriverShipments(Long driverId) {
        return shipmentRepository.findByAssignedDriverId(driverId);
    }

    public Long getShipmentCountByStatus(ShipmentStatus status) {
        return shipmentRepository.countByStatus(status);
    }

    public List<Shipment> getShipmentsDueForDelivery(LocalDateTime start, LocalDateTime end) {
        return shipmentRepository.findShipmentsDueForDelivery(start, end);
    }
}