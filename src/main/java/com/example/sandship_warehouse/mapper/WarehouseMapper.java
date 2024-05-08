package com.example.sandship_warehouse.mapper;

import com.example.sandship_warehouse.dto.WarehouseDto;
import com.example.sandship_warehouse.model.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public Warehouse mapToEntity(WarehouseDto dto) {
        final Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        return warehouse;
    }

    public WarehouseDto mapToDto(Warehouse warehouse) {
        final WarehouseDto dto = new WarehouseDto();
        dto.setName(warehouse.getName());
        return dto;
    }
}
