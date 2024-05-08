package com.example.sandship_warehouse.service.impl;

import com.example.sandship_warehouse.exception.NotFoundException;
import com.example.sandship_warehouse.model.MaterialType;
import com.example.sandship_warehouse.model.Player;
import com.example.sandship_warehouse.model.PlayerWarehouseLink;
import com.example.sandship_warehouse.model.Warehouse;
import com.example.sandship_warehouse.repository.MaterialTypeRepository;
import com.example.sandship_warehouse.repository.PlayerRepository;
import com.example.sandship_warehouse.repository.PlayerWarehouseLinkRepository;
import com.example.sandship_warehouse.repository.WarehouseRepository;
import com.example.sandship_warehouse.service.MaterialTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MaterialTypeServiceImpl implements MaterialTypeService {

    private final PlayerWarehouseLinkRepository playerWarehouseLinkRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final WarehouseRepository warehouseRepository;
    private final PlayerRepository playerRepository;


    @Override
    @Transactional
    public void createMaterialType(MaterialType materialType) {
        if (materialTypeRepository.existsByName(materialType.getName())) {
            throw new IllegalArgumentException("Material type name is already in use");
        }
        materialTypeRepository.save(materialType);
    }

    @Override
    public MaterialType getMaterialTypeById(long id) {
        return materialTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Material type not found"));
    }

    @Override
    @Transactional
    public MaterialType editMaterialType(long id, MaterialType newMaterialType) {
        final MaterialType materialType = materialTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Material type not found"));
        if (materialTypeRepository.existsByName(materialType.getName())) {
            throw new IllegalArgumentException("Material type name is already in use");
        }
        materialType.setName(newMaterialType.getName());
        materialType.setIcon(newMaterialType.getIcon());
        materialType.setDescription(newMaterialType.getDescription());
        materialTypeRepository.saveAndFlush(materialType);
        return materialType;
    }

    @Override
    @Transactional
    public void addMaterialTypeToWarehouse(long warehouseId, long playerId, long materialTypeId, long quantity) {
        final Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new NotFoundException("Player not found"));

        final Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new NotFoundException("Warehouse not found"));

        final MaterialType materialType = materialTypeRepository.findById(materialTypeId)
                .orElseThrow(() -> new NotFoundException("Material type not found"));

        final PlayerWarehouseLink playerWarehouseLink = new PlayerWarehouseLink();
        playerWarehouseLink.setPlayer(player);
        playerWarehouseLink.setWarehouse(warehouse);
        playerWarehouseLink.setMaterialType(materialType);
        playerWarehouseLink.setQuantity(quantity);
        playerWarehouseLinkRepository.saveAndFlush(playerWarehouseLink);
    }

    @Override
    @Transactional
    public void editMaterialTypeToWarehouse(long playerWarehouseLinkId, Long newWarehouseId, long quantity) {
        final PlayerWarehouseLink playerWarehouseLink = playerWarehouseLinkRepository.findById(playerWarehouseLinkId)
                .orElseThrow(() -> new NotFoundException("Player Warehouse link not found"));
        if (quantity >= 0) {
            playerWarehouseLink.setQuantity(playerWarehouseLink.getQuantity() + quantity);
        } else {
            playerWarehouseLink.setQuantity(playerWarehouseLink.getQuantity() - quantity);
            if (playerWarehouseLink.getQuantity() < 0) {
                playerWarehouseLinkRepository.delete(playerWarehouseLink);
            }
        }
        //Transfer to new warehouse
        if (newWarehouseId != null) {
            final Warehouse warehouse = warehouseRepository.findById(newWarehouseId)
                    .orElseThrow(() -> new NotFoundException("Warehouse not found"));
            playerWarehouseLink.setWarehouse(warehouse);
        }
        playerWarehouseLinkRepository.saveAndFlush(playerWarehouseLink);
    }

    @Override
    @Transactional
    public void deletePlayerWarehouseLink(long playerWarehouseLinkId) {
        if (!playerWarehouseLinkRepository.existsById(playerWarehouseLinkId)) {
            throw new NotFoundException("Player Warehouse link not found");
        }
        playerWarehouseLinkRepository.deleteById(playerWarehouseLinkId);
    }

    //I would also add a method here for “reordering of materials”
}
