package com.example.sandship_warehouse.controller;

import com.example.sandship_warehouse.dto.WarehouseDto;
import com.example.sandship_warehouse.mapper.WarehouseMapper;
import com.example.sandship_warehouse.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final WarehouseMapper warehouseMapper;


    @PostMapping()
    public ResponseEntity<String> createWarehouse(@RequestBody WarehouseDto dto) {
        warehouseService.createWarehouse(warehouseMapper.mapToEntity(dto));
        return new ResponseEntity<>("Warehouse created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable long id) {
        return ResponseEntity.ok(warehouseMapper.mapToDto(warehouseService.getWarehouseById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDto warehouseDto) {
        return ResponseEntity.ok(warehouseMapper.mapToDto(
                warehouseService.editWarehouse(id, warehouseMapper.mapToEntity(warehouseDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
