package com.ems.service;

import com.ems.entity.Player;

import java.util.List;

public interface PlayerService {
    public Player savePlayer(Player player);

    public List<Player> allPlayers();
    public List<Player> findByTeam(String team);
    public Player getById(int id);
    public  boolean isExist(Integer team);
}
