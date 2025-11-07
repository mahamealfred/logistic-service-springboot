package com.code_with_alfred.logistics_company.logistics.dto.response;




import com.code_with_alfred.logistics_company.logistics.entity.ShipmentStatus;
import com.code_with_alfred.logistics_company.logistics.entity.ShipmentType;
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
public class ShipmentResponse {
    private Long id;
    private String trackingNumber;
    private String origin;
    private String destination;
    private ShipmentStatus status;
    private ShipmentType type;
    private BigDecimal weight;
    private BigDecimal volume;
    private BigDecimal declaredValue;
    private String senderName;
    private String receiverName;
    private String driverName;
    private LocalDateTime pickupDate;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime actualDeliveryDate;
    private LocalDateTime createdAt;
}