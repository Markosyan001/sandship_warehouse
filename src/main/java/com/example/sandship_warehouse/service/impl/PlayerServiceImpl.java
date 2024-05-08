package com.example.sandship_warehouse.service.impl;

import com.example.sandship_warehouse.exception.NotFoundException;
import com.example.sandship_warehouse.model.Player;
import com.example.sandship_warehouse.repository.PlayerRepository;
import com.example.sandship_warehouse.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public void createPlayer(Player player) {

        if (playerRepository.existsByNicName(player.getNicName())) {
            throw new IllegalArgumentException("Nickname is already in use");
        }
        playerRepository.save(player);
    }

    @Override
    @Transactional
    public Player editPlayer(long id, Player editedPlayer) {
        if (playerRepository.existsByNicName(editedPlayer.getNicName())) {
            throw new IllegalArgumentException("Nickname is already in use");
        }
        final Player player = playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Player not found"));
        player.setNicName(editedPlayer.getNicName());

        return playerRepository.saveAndFlush(player);
    }

    @Override
    public Player getPlayerById(long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Player not found"));
    }

    @Override
    @Transactional
    public void deletePlayerById(long id) {
        if (playerRepository.existsById(id)) {
            throw new NotFoundException("Player not found");
        }
        playerRepository.deleteById(id);
    }
}
