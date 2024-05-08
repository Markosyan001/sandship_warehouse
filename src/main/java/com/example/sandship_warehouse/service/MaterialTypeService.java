package com.example.sandship_warehouse.service;

import com.example.sandship_warehouse.model.MaterialType;

public interface MaterialTypeService {

    void createMaterialType(MaterialType materialType);

    MaterialType getMaterialTypeById(long id);

    MaterialType editMaterialType(long id, MaterialType materialType);

    void addMaterialTypeToWarehouse(long warehouseId, long playerId, long materialTypeId, long quantity);

    void editMaterialTypeToWarehouse(long playerWarehouseLinkId, Long newWarehouseId, long quantity);

    void deletePlayerWarehouseLink(long playerWarehouseLinkId);

}
