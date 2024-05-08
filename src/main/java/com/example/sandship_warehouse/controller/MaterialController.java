package com.example.sandship_warehouse.controller;

import com.example.sandship_warehouse.dto.MaterialTypeDto;
import com.example.sandship_warehouse.mapper.MaterialTypeMapper;
import com.example.sandship_warehouse.model.MaterialType;
import com.example.sandship_warehouse.service.MaterialTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/material")
public class MaterialController {

    private final MaterialTypeService materialTypeService;
    private final MaterialTypeMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<?> createMaterialType(@RequestBody MaterialTypeDto dto) {
        materialTypeService.createMaterialType(mapper.mapToEntity(dto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMaterialTypeById(@PathVariable long id) {
        final MaterialTypeDto materialTypeDto = mapper.mapToDto(materialTypeService.getMaterialTypeById(id));
        return ResponseEntity.ok(materialTypeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMaterialType(@PathVariable long id, @RequestBody MaterialType materialType) {
        final MaterialTypeDto materialTypeDto = mapper.mapToDto(materialTypeService.editMaterialType(id, materialType));
        return ResponseEntity.ok(materialTypeDto);
    }

    @PostMapping("/add-to-warehouse")
    public ResponseEntity<?> addMaterialTypeToWarehouse(@RequestParam long warehouseId,
                                                           @RequestParam long playerId,
                                                           @RequestParam long materialTypeId,
                                                           @RequestParam long quantity) {
        materialTypeService.addMaterialTypeToWarehouse(warehouseId, playerId, materialTypeId, quantity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit-to-warehouse/{playerWarehouseLinkId}")
    public ResponseEntity<?> editMaterialTypeToWarehouse(@PathVariable long playerWarehouseLinkId,
                                                            @RequestParam(required = false) Long newWarehouseId,
                                                            @RequestParam long quantity) {
        materialTypeService.editMaterialTypeToWarehouse(playerWarehouseLinkId, newWarehouseId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/player-warehouse-link/{playerWarehouseLinkId}")
    public ResponseEntity<?> deletePlayerWarehouseLink(@PathVariable long playerWarehouseLinkId) {
        materialTypeService.deletePlayerWarehouseLink(playerWarehouseLinkId);
        return ResponseEntity.ok().build();
    }
}
