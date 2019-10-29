package com.dartsgame.controllers;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.services.GameService;
import com.dartsgame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "startGame", method = RequestMethod.GET)
    public String startGame(Model model){
        model.addAttribute("game", new Game());
        return "numberOfPlayers";
    }

    @RequestMapping(value = "startGame", method = RequestMethod.POST)
    public String startGame(@ModelAttribute Game game){
        gameService.saveGame(game);
        return "redirect:players/" + game.getId();
    }

    @RequestMapping(value = "players/{gameId}", method = RequestMethod.GET)
    public String players(@PathVariable int gameId,Model model){
        Game g = gameService.findGameById(gameId);
        model.addAttribute("player", new Player());
        model.addAttribute("game", g);
/*        List<String> listOfPlayers = new ArrayList<>();
        for(int i = 1; i <= g.getNumberOfPlayers(); i++) {
            listOfPlayers.add("Player " + i);
        }*/
        return "players";
    }

    int counter = 0;

    @RequestMapping(value = "players/{gameId}", method = RequestMethod.POST)
    public String players(@ModelAttribute Player player, @PathVariable int gameId){
        Game g = gameService.findGameById(gameId);
        int max = g.getNumberOfPlayers();
        player.setGame(g);
        playerService.savePlayer(player);
        counter += 1;
        if (counter!=max) {
            System.out.println(counter);
            return "players";
        }
        return "start";
    }

    @RequestMapping("start")
    public String start(){
        return "start";
    }

    @ModelAttribute("team")
    public List<Player> getTeam(){
        Game g = gameService.findGameById(1);   //póki co na sztywno id=1
        return playerService.findAllByGame(g);
    }
}
