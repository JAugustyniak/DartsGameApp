package com.dartsgame.controllers;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.model.Point;
import com.dartsgame.services.GameService;
import com.dartsgame.services.PlayerService;
import com.dartsgame.services.PointService;
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

    @Autowired
    PointService pointService;

    @RequestMapping(value = "startGame", method = RequestMethod.GET)
    public String startGame(Model model){
        model.addAttribute("game", new Game());
        return "numberOfPlayers";
    }

    @RequestMapping(value = "startGame", method = RequestMethod.POST)
    public String startGame(@ModelAttribute Game game){
        gameService.saveGame(game);
        return "redirect:/" + game.getId();
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public String enterNickname(@PathVariable Integer gameId, Model model){
//        model.addAttribute("game", gameService.findGameById(gameId));
        model.addAttribute("player", new Player());
        return "addPlayer";
    }
    //ZMIANA
    int counterAddPlayer = 0;
    @RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
    public String processEnterNickname(@PathVariable Integer gameId, @ModelAttribute Player player, @ModelAttribute Point point) {
        Game g = gameService.findGameById(gameId);
        player.setGame(g);
        playerService.savePlayer(player);
        point.setRound(1);
        counterAddPlayer += 1;
        if(counterAddPlayer < g.getNumberOfPlayers()){
          return "home/" + g.getId();
        }
        List<Player> listOfPlayers = playerService.findAllByGame(g); // TUTAJ TRZEBA POBRAC LISTE GRACZY
        return "redirect:/" + g.getId() + "/" + listOfPlayers.wezpierwszegograczaijegoid + "/" + point.getRound(); //TUTAJ MUSI POBRAC ID PIERWSZEGO GRACZA Z LISTY

    }
    //KONIEC ZMIANY
    @RequestMapping(value = "/{gameId}/{playerId}", method = RequestMethod.GET)
    public String addPoints(@PathVariable Integer gameId, @PathVariable Integer playerId, Model model){
        Player player = playerService.findPlayerById(playerId);
        Point point = new Point();
        point.setPlayer(player);
        model.addAttribute("point", point);
        return "addPoints";
    }

    @RequestMapping(value = "/{gameId}/{playerId}", method = RequestMethod.POST)
    public String addPoints(@ModelAttribute("point") Point point, @PathVariable("playerId") Integer playerId, Model model){
        Player player = playerService.findPlayerById(playerId);
        point.setPlayer(player);
        pointService.savePoint(point);
        model.addAttribute("sum", 301 - pointService.getSumPoints(playerService.findPlayerById(playerId)));
        return "addPoints";
    }














//    @RequestMapping(value = "players/{gameId}", method = RequestMethod.GET)
//    public String players(@PathVariable int gameId,Model model){
//        Game g = gameService.findGameById(gameId);
//        model.addAttribute("player", new Player());
//        model.addAttribute("game", g);
///*        List<String> listOfPlayers = new ArrayList<>();
//        for(int i = 1; i <= g.getNumberOfPlayers(); i++) {
//            listOfPlayers.add("Player " + i);
//        }*/
//        return "players";
//    }
//
//    int counter = 0;
//
//    @RequestMapping(value = "players/{gameId}", method = RequestMethod.POST)
//    public String players(@ModelAttribute Player player, @PathVariable int gameId, HttpSession request){
//        Game g = gameService.findGameById(gameId);
//        int max = g.getNumberOfPlayers();
//        player.setGame(g);
//        playerService.savePlayer(player);
//        counter += 1;
//        if (counter!=max) {
//            System.out.println(counter);
//            return "players";
//        }
////        request.setAttribute("listOfPlayers", playerService.findAllByGame(g));
////        List<Player> test = (List<Player>) request.getAttribute("listOfPlayers");
//
//        return "redirect:/players/" + g.getId() + "/" + playerService.findAllByGame(g).get(0).getId() + "/" + "1";//przekierowanie pierwszego gracza do pierwszej rundy
//    }
//
//    @RequestMapping(value = "players/{gameId}/{playerId}/{throwNumber}", method = RequestMethod.GET)
//    @ResponseBody
//    public String addPoints(@PathVariable Integer gameId, @PathVariable Integer playerId, @PathVariable Integer throwNumber, Model model){
////        model.addAttribute("player", playerService.findPlayerById(playerId));
//        return "abc";
//
//    }
//
//    @RequestMapping("start")
//    public String start(){
//        return "start";
//    }
//
//    @ModelAttribute("team")
//    public List<Player> getTeam(){
//        Game g = gameService.findGameById(1);   //póki co na sztywno id=1
//        return playerService.findAllByGame(g);
//    }
}
