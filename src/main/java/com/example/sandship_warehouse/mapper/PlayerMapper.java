package com.example.sandship_warehouse.mapper;

import com.example.sandship_warehouse.dto.PlayerDto;
import com.example.sandship_warehouse.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player mapToEntity(PlayerDto dto) {
        final Player player = new Player();
        player.setNicName(dto.getNicName());
        return player;
    }

    public PlayerDto mapToDto(Player player) {
        final PlayerDto dto = new PlayerDto();
        dto.setNicName(player.getNicName());
        return dto;
    }
}
