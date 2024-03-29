package com.dartsgame.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Winner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "round_id")
    private Integer round;

    public Winner(String name, Game game, Player player, Integer round) {
        this.name = name;
        this.game = game;
        this.player = player;
        this.round = round;
    }

    public Winner(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
