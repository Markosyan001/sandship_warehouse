package com.example.sandship_warehouse.repository;

import com.example.sandship_warehouse.model.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Long> {

    boolean existsByName(String name);
}
