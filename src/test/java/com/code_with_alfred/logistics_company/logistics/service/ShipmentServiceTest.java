package com.code_with_alfred.logistics_company.logistics.service;


import com.code_with_alfred.logistics_company.logistics.dto.request.ShipmentCreateRequest;
import com.code_with_alfred.logistics_company.logistics.entity.Shipment;
import com.code_with_alfred.logistics_company.logistics.entity.ShipmentStatus;
import com.code_with_alfred.logistics_company.logistics.entity.ShipmentType;
import com.code_with_alfred.logistics_company.logistics.entity.User;
import com.code_with_alfred.logistics_company.logistics.repository.ShipmentRepository;
import com.code_with_alfred.logistics_company.logistics.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    private User sender;
    private User receiver;
    private ShipmentCreateRequest request;

    @BeforeEach
    void setUp() {
        sender = new User();
        sender.setId(1L);
        sender.setEmail("sender@test.com");

        receiver = new User();
        receiver.setId(2L);
        receiver.setEmail("receiver@test.com");

        request = ShipmentCreateRequest.builder()
                .origin("New York")
                .destination("Los Angeles")
                .type(ShipmentType.STANDARD)
                .weight(BigDecimal.valueOf(10.5))
                .volume(BigDecimal.valueOf(0.5))
                .declaredValue(BigDecimal.valueOf(1000))
                .senderId(1L)
                .receiverId(2L)
                .pickupDate(LocalDateTime.now().plusDays(1))
                .estimatedDeliveryDate(LocalDateTime.now().plusDays(3))
                .build();
    }

    @Test
    void createShipment_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));
        when(shipmentRepository.save(any(Shipment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Shipment result = shipmentService.createShipment(request);

        assertNotNull(result);
        assertEquals("New York", result.getOrigin());
        assertEquals("Los Angeles", result.getDestination());
        assertEquals(ShipmentStatus.PENDING, result.getStatus());
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
    }
}