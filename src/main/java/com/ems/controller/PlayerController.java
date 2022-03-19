package com.ems.controller;

import com.ems.entity.Player;
import com.ems.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/allPlayers")
    public List<Player> allPlayers() {
        return playerService.allPlayers();

    }

    @PostMapping("/savePlayer")
    public ResponseEntity<String> savePlayer(@RequestBody Player player) {
        playerService.savePlayer(player);
        ResponseEntity response = null;
        Player id = playerService.savePlayer(player);
        response = new ResponseEntity("Player save with id " + id, HttpStatus.CREATED);
        return response;

    }


    @GetMapping("/findByTeam/{team}")
    public List<Player> findByTeam(@PathVariable("team") String team) {
        return playerService.findByTeam(team);


//        @GetMapping("/getById/{id}")
//        public ResponseEntity<String> getPlayer (@PathVariable Integer id){
//            Optional<Player> player = Optional.ofNullable(playerService.getById(id));
//            ResponseEntity responseEntity = null;
//            if (player.isPresent()) {
//                responseEntity = new ResponseEntity<Player>(player.get(), HttpStatus.OK);
//            } else {
//                responseEntity = new ResponseEntity<String>("Player not found for id " + id, HttpStatus.BAD_REQUEST);
//            }
//            return responseEntity;
//        }
    }

}
