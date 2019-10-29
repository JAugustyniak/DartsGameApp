package com.dartsgame.controllers;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.services.GameService;
import com.dartsgame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
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
    public String players(@ModelAttribute Player player, @PathVariable int gameId, HttpSession request){
        Game g = gameService.findGameById(gameId);
        int max = g.getNumberOfPlayers();
        player.setGame(g);
        playerService.savePlayer(player);
        counter += 1;
        if (counter!=max) {
            System.out.println(counter);
            return "players";
        }
//        request.setAttribute("listOfPlayers", playerService.findAllByGame(g));
//        List<Player> test = (List<Player>) request.getAttribute("listOfPlayers");

        return "redirect:/players/" + g.getId() + "/" + playerService.findAllByGame(g).get(0).getId() + "/" + "1";//przekierowanie pierwszego gracza do pierwszej rundy
    }

    @RequestMapping(value = "players/{gameId}/{playerId}/{throwNumber}", method = RequestMethod.GET)
    @ResponseBody
    public String addPoints(@PathVariable Integer gameId, @PathVariable Integer playerId, @PathVariable Integer throwNumber, Model model){
//        model.addAttribute("player", playerService.findPlayerById(playerId));
        return "abc";

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
