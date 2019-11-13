package com.dartsgame.controllers;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.model.Point;
import com.dartsgame.model.Winner;
import com.dartsgame.services.GameService;
import com.dartsgame.services.PlayerService;
import com.dartsgame.services.PointService;
import com.dartsgame.services.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    PointService pointService;

    @Autowired
    WinnerService winnerService;

    @RequestMapping(value = "startGame", method = RequestMethod.GET)
    public String startGame(Model model) {
        model.addAttribute("game", new Game());
        return "numberOfPlayers";
    }

    @RequestMapping(value = "startGame", method = RequestMethod.POST)
    public String startGame(@ModelAttribute Game g, Model model) {
        gameService.saveGame(g);
        return "redirect:/" + g.getId();
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public String enterNickname(@PathVariable Integer gameId, Model model) {
//        model.addAttribute("game", gameService.findGameById(gameId));
        model.addAttribute("player", new Player());
        return "addPlayer";
    }

//    @RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
//    public String processEnterNickname(@PathVariable Integer gameId, @ModelAttribute Player player, @ModelAttribute Point point) {
//        Game g = gameService.findGameById(gameId);
//        player.setGame(g);
//        playerService.savePlayer(player);
//        point.setRound(1);
//        return "redirect:/" + g.getId() + "/" + player.getId() + "/" + point.getRound();
//    }

    //    int counterAddPlayer = 0;
    @RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
    public String processEnterNickname(@PathVariable Integer gameId, @ModelAttribute Player player, @ModelAttribute Point point) {
        Game g = gameService.findGameById(gameId);
        player.setGame(g);
        playerService.savePlayer(player);
        point.setRound(1);
//        counterAddPlayer += 1;
        if (playerService.findAllByGame(g).size() < g.getNumberOfPlayers()) {
            return "addPlayer";
        }
//        if(counterAddPlayer < g.getNumberOfPlayers()){
//
//        }
        List<Player> listOfPlayers = playerService.findAllByGame(g); // TUTAJ TRZEBA POBRAC LISTE GRACZY
        //TUTAJ MUSI POBRAC ID PIERWSZEGO GRACZA Z LISTY
//        counterAddPlayer = 0;
        return "redirect:/" + g.getId() + "/" + listOfPlayers.get(0).getId() + "/" + point.getRound();


    }

    @RequestMapping(value = "/{gameId}/{playerId}/{roundId}", method = RequestMethod.GET)
    public String addPoints(@PathVariable Integer gameId, @PathVariable Integer playerId, @PathVariable Integer roundId, Model model) {
        Player player = playerService.findPlayerById(playerId);
        Point point = new Point();
        point.setPlayer(player);
        point.setRound(roundId);
        model.addAttribute("point", point);
        model.addAttribute("playerName", player.getNickName());
        if(roundId > 1){
            int sum = 301 - pointService.getSumPoints(player);
            model.addAttribute("sum", sum);
        }

        return "addPoints";
    }

//    int numberOfThrows = 0;
//
//    @RequestMapping(value = "/{gameId}/{playerId}/{roundId}", method = RequestMethod.POST)
//    public String addPoints(@ModelAttribute Point point, @PathVariable Integer gameId, @PathVariable Integer playerId, @PathVariable Integer roundId, Model model) {
//        Player player = playerService.findPlayerById(playerId);
//        point.setPlayer(player);
//        point.setRound(roundId);
//        pointService.savePoint(point);
//        numberOfThrows += 1;
//        int sum = 301 - pointService.getSumPoints(playerService.findPlayerById(playerId));
//        if (sum == 0) {
//            model.addAttribute("roundId", roundId);
//            model.addAttribute("player", player);
//            return "win";
//        }
//        model.addAttribute("sum", sum);
//        if (numberOfThrows % 3 != 0) {
//            return "addPoints";
//        }
//        int round = roundId + 1;
//        return "redirect:/" + gameId + "/" + playerId + "/" + round;
//    }


    int numberOfThrows = 0;
    int counterAddPoints = 0;

    @RequestMapping(value = "/{gameId}/{playerId}/{roundId}", method = RequestMethod.POST)
    public String addPoints(@ModelAttribute Point point, @PathVariable Integer gameId, @PathVariable Integer playerId, @PathVariable Integer roundId, Model model) {
        Game g = gameService.findGameById(gameId);
        Player player = playerService.findPlayerById(playerId);
        point.setPlayer(player);
        point.setRound(roundId);
        numberOfThrows += 1;
        point.setThrowNumber(numberOfThrows);
        model.addAttribute("throwNumber", numberOfThrows + 1);
        String name = playerService.findPlayerById(playerId).getNickName();
        model.addAttribute("playerName", name);
        pointService.savePoint(point);
        int sum = 301 - pointService.getSumPoints(playerService.findPlayerById(playerId));


        if (sum == 0) {
            model.addAttribute("roundId", roundId);
            model.addAttribute("player", player);
            Winner winner = new Winner();
            winner.setGame(g);
            winner.setPlayer(player);
            winner.setRound(roundId);
            String winnerName = player.getNickName();
            winner.setName(winnerName);
            winnerService.saveWinner(winner);
            return "win";
        }

        model.addAttribute("sum", sum);
        if (numberOfThrows < 3) {
            return "addPoints";
        }

        List<Player> listOfPlayers = playerService.findAllByGame(g);
        if (numberOfThrows >= 3 /*|| (pointService.getSumPoints(playerService.findPlayerById(playerId)) > sum)*/) {
            numberOfThrows = 0;
            counterAddPoints += 1;

            //pobracliste zawodnikow
            //ustawic kolejnego gracza pobierajac licznik jako index listy
            if (counterAddPoints >= listOfPlayers.size()) {
                int round = roundId + 1;
                counterAddPoints = 0;
                return "redirect:/" + gameId + "/" + listOfPlayers.get(0).getId() + "/" + round;
            }
            return "redirect:/" + gameId + "/" + listOfPlayers.get(counterAddPoints).getId() + "/" + roundId;
        }
        //Przejscie do nastepnej rundy, czyli zaczynamy znow od pierwszego gracza
        return "abc";
    }

    @RequestMapping(value = "/listOfWinners", method = RequestMethod.GET)
    public String showWinners(Model model) {
        List<Winner> winners = winnerService.getAllWinners();
        model.addAttribute("winners", winners);
        return "listOfWinners";
    }

    @RequestMapping(value = "/listOfWinners", method = RequestMethod.POST)
    public String processShowWinners(@RequestParam Integer numberOfWinners) {
        return "redirect:/listOfWinners/" + numberOfWinners;
    }

    @RequestMapping(value = "/listOfWinners/{numberOfWinners}", method = RequestMethod.GET)
    public String showWinnersN(@PathVariable Integer numberOfWinners, Model model) {
        model.addAttribute("NLastWinners", winnerService.getNumberOfLastWinners(numberOfWinners));
        return "NLastListWinners";
    }

    @RequestMapping(value = "/showHistory/{gameId}", method = RequestMethod.GET)
    public String showHistory(@PathVariable Integer gameId, Model model) {
        Game g = gameService.findGameById(gameId);
        model.addAttribute("historyOfWinnerMatch", pointService.getcostam(g));
        return "showHistoryOfWinnerMatch";
//        pointService.getcostam(g).get(0).getPlayer().getNickName();

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
