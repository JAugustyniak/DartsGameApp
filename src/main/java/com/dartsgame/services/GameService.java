package com.dartsgame.services;

import com.dartsgame.model.Game;
import com.dartsgame.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService{

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public void saveGame(Game game){
        gameRepository.save(game);
    }

    public Game findGameById(int id){
        return gameRepository.findTopByIdOrderByIdAsc(id);
    }
}

