package com.code_with_alfred.logistics_company.logistics.dto.request;

import com.logistics.entity.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentUpdateRequest {
    private Long assignedDriverId;
    private ShipmentStatus status;
    private LocalDateTime estimatedDeliveryDate;
}