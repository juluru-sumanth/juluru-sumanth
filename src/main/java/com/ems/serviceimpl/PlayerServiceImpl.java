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
    public Player savePlayer(Player player) {
        return playerRepository.save(player);

    }

    @Override
    public List<Player> allPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findByTeam(String team) {
        return playerRepository.findByTeam(team);
    }

    @Override
    public Player getById(int id) {
        return playerRepository.findById(id).get();
    }

    @Override
    public boolean isExist(Integer team) {
        return playerRepository.existsById(team);
    }
}
