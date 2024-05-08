package com.example.sandship_warehouse.repository;

import com.example.sandship_warehouse.model.PlayerWarehouseLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerWarehouseLinkRepository extends JpaRepository<PlayerWarehouseLink, Long> {
}
