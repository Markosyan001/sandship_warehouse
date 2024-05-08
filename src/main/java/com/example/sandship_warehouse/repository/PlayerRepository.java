package com.example.sandship_warehouse.repository;

import com.example.sandship_warehouse.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByNicName(String nicName);
}
