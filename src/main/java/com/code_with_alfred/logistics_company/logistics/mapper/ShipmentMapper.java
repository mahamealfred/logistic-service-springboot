package com.code_with_alfred.logistics_company.logistics.mapper;


import com.code_with_alfred.logistics_company.logistics.dto.request.ShipmentCreateRequest;
import com.code_with_alfred.logistics_company.logistics.dto.response.ShipmentResponse;
import com.code_with_alfred.logistics_company.logistics.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    ShipmentMapper INSTANCE = Mappers.getMapper(ShipmentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trackingNumber", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "actualDeliveryDate", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    @Mapping(target = "assignedDriver", ignore = true)
    Shipment toEntity(ShipmentCreateRequest request);

    @Mapping(source = "sender.firstName", target = "senderName")
    @Mapping(source = "receiver.firstName", target = "receiverName")
    @Mapping(source = "assignedDriver.firstName", target = "driverName")
    ShipmentResponse toResponse(Shipment shipment);
}