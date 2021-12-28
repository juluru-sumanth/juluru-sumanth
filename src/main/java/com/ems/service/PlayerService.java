package com.ems.service;

import com.ems.entity.Player;

import java.util.List;

public interface PlayerService {
    public void savePlayer(Player player);

    public List<Player> allPlayers();
}
