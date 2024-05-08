package com.example.sandship_warehouse.mapper;

import com.example.sandship_warehouse.dto.MaterialTypeDto;
import com.example.sandship_warehouse.model.MaterialType;
import org.springframework.stereotype.Component;

@Component
public class MaterialTypeMapper {

    public MaterialType mapToEntity(MaterialTypeDto dto) {
        final MaterialType materialType = new MaterialType();
        materialType.setName(dto.getName());
        materialType.setIcon(dto.getIcon());
        materialType.setDescription(dto.getDescription());
        materialType.setMaxCapacity(dto.getMaxCapacity());
        return materialType;
    }

    public MaterialTypeDto mapToDto(MaterialType materialType) {
        final MaterialTypeDto dto = new MaterialTypeDto();
        dto.setDescription(materialType.getDescription());
        dto.setIcon(materialType.getIcon());
        dto.setName(materialType.getName());
        dto.setMaxCapacity(materialType.getMaxCapacity());
        return dto;
    }
}
