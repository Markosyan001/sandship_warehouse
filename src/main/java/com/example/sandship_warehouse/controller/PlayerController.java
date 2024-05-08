package com.example.sandship_warehouse.controller;

import com.example.sandship_warehouse.dto.PlayerDto;
import com.example.sandship_warehouse.mapper.PlayerMapper;
import com.example.sandship_warehouse.model.Player;
import com.example.sandship_warehouse.service.PlayerService;
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
@RequestMapping(value = "/player")
public class PlayerController {

    public final PlayerService playerService;
    public final PlayerMapper playerMapper;

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDto dto) {
        playerService.createPlayer(playerMapper.mapToEntity(dto));
        return new ResponseEntity<>("Player created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable long id) {
        final PlayerDto playerDto = playerMapper.mapToDto(playerService.getPlayerById(id));
        return ResponseEntity.ok(playerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPlayer(@PathVariable long id,
                                        @RequestBody PlayerDto dto) {
        final Player player = playerService.editPlayer(id, playerMapper.mapToEntity(dto));
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }
}
