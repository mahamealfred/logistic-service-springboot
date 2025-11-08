package com.code_with_alfred.logistics_company.logistics.controller;


import com.code_with_alfred.logistics_company.logistics.dto.request.ShipmentCreateRequest;
import com.code_with_alfred.logistics_company.logistics.dto.request.ShipmentUpdateRequest;
import com.code_with_alfred.logistics_company.logistics.dto.response.ApiResponse;
import com.code_with_alfred.logistics_company.logistics.entity.Shipment;
import com.code_with_alfred.logistics_company.logistics.entity.ShipmentStatus;
import com.code_with_alfred.logistics_company.logistics.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<Shipment>> createShipment(@Valid @RequestBody ShipmentCreateRequest request) {
        Shipment shipment = shipmentService.createShipment(request);
        return ResponseEntity.ok(ApiResponse.success("Shipment created successfully", shipment));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Shipment>>> getAllShipments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Shipment> shipments = shipmentService.getAllShipments(pageable);
        return ResponseEntity.ok(ApiResponse.success(shipments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Shipment>> getShipmentById(@PathVariable Long id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        return ResponseEntity.ok(ApiResponse.success(shipment));
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ApiResponse<Shipment>> getShipmentByTrackingNumber(@PathVariable String trackingNumber) {
        Shipment shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(ApiResponse.success(shipment));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Shipment>>> getShipmentsByStatus(@PathVariable ShipmentStatus status) {
        List<Shipment> shipments = shipmentService.getShipmentsByStatus(status);
        return ResponseEntity.ok(ApiResponse.success(shipments));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'DRIVER')")
    public ResponseEntity<ApiResponse<Shipment>> updateShipmentStatus(
            @PathVariable Long id,
            @RequestParam ShipmentStatus status) {
        Shipment shipment = shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Shipment status updated successfully", shipment));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse<Shipment>> updateShipment(
            @PathVariable Long id,
            @Valid @RequestBody ShipmentUpdateRequest request) {
        Shipment shipment = shipmentService.updateShipment(id, request);
        return ResponseEntity.ok(ApiResponse.success("Shipment updated successfully", shipment));
    }

    @GetMapping("/driver/{driverId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'DRIVER')")
    public ResponseEntity<ApiResponse<List<Shipment>>> getDriverShipments(@PathVariable Long driverId) {
        List<Shipment> shipments = shipmentService.getDriverShipments(driverId);
        return ResponseEntity.ok(ApiResponse.success(shipments));
    }
}
