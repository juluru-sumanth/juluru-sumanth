package com.ems.serviceimpl;

import com.ems.entity.Player;
import com.ems.repository.PlayerRepository;
import com.ems.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void savePlayer(Player player) {
        this.playerRepository.save(player);

    }

    @Override
    public List<Player> allPlayers() {
        return playerRepository.findAll();
    }
}
