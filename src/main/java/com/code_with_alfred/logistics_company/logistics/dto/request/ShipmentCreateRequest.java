package com.code_with_alfred.logistics_company.logistics.dto.request;

import com.code_with_alfred.logistics_company.logistics.entity.ShipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentCreateRequest {
    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Type is required")
    private ShipmentType type;

    private BigDecimal weight;
    private BigDecimal volume;
    private BigDecimal declaredValue;

    @NotNull(message = "Sender ID is required")
    private Long senderId;

    @NotNull(message = "Receiver ID is required")
    private Long receiverId;

    private Long assignedDriverId;
    private LocalDateTime pickupDate;
    private LocalDateTime estimatedDeliveryDate;
}