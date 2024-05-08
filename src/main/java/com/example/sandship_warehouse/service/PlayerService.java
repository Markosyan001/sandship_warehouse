package com.example.sandship_warehouse.service;

import com.example.sandship_warehouse.model.Player;

public interface PlayerService {

    void createPlayer(Player player);

    Player editPlayer(long id, Player player);

    Player getPlayerById(long id);

    void deletePlayerById(long id);

}
