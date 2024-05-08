package com.example.sandship_warehouse.service;

import com.example.sandship_warehouse.model.Warehouse;

public interface WarehouseService {

    void createWarehouse(Warehouse warehouse);

    Warehouse getWarehouseById(long id);

    Warehouse editWarehouse(long id, Warehouse warehouse);

    void deleteWarehouse(long id);

}
