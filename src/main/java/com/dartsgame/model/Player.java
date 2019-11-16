package com.dartsgame.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nickName;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    public Player() {
    }

    public Player(String nickName, Game game) {
        this.nickName = nickName;
        this.game = game;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
