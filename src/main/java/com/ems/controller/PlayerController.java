package com.ems.controller;

import com.ems.entity.Player;
import com.ems.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/allPlayers")
    public List<Player> allPlayers(){
        return playerService.allPlayers();

    }

    @PostMapping("/savePlayer")

    public void savePlayer(@RequestBody Player player){
         playerService.savePlayer(player);
    }
}
