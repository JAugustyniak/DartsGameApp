package com.dartsgame.services;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public void savePlayer(Player player){
        playerRepository.save(player);
    }

    public List<Player> findAllByGame(Game game){
        return playerRepository.findAllByGame(game);
    }

    public Player findPlayerById(Integer id){
        return playerRepository.findPlayerById(id);
    }

}
