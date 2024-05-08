package com.example.sandship_warehouse.service.impl;

import com.example.sandship_warehouse.exception.NotFoundException;
import com.example.sandship_warehouse.model.Warehouse;
import com.example.sandship_warehouse.repository.WarehouseRepository;
import com.example.sandship_warehouse.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Override
    @Transactional
    public void createWarehouse(Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName())) {
            throw new IllegalArgumentException("Name warehouse is already in use");
        }
        warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse getWarehouseById(long id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found"));
    }

    @Override
    @Transactional
    public Warehouse editWarehouse(long id, Warehouse newWarehouse) {
        final Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found"));

        if (warehouseRepository.existsByName(warehouse.getName())) {
            throw new IllegalArgumentException("Name warehouse is already in use");
        }
        warehouse.setName(newWarehouse.getName());
        return warehouseRepository.saveAndFlush(warehouse);
    }

    @Override
    @Transactional
    public void deleteWarehouse(long id) {
        warehouseRepository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found"));
        warehouseRepository.deleteById(id);
    }
}
