package com.dartsgame.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Integer throwValue;

    private Integer throwNumber;

    private Integer round;

    public Point() {
    }

    public Point(Player player, Integer throwValue, Integer throwNumber, Integer round) {
        this.player = player;
        this.throwValue = throwValue;
        this.throwNumber = throwNumber;
        this.round = round;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getThrowValue() {
        return throwValue;
    }

    public void setThrowValue(Integer throwValue) {
        this.throwValue = throwValue;
    }

    public Integer getThrowNumber() {
        return throwNumber;
    }

    public void setThrowNumber(Integer throwNumber) {
        this.throwNumber = throwNumber;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
