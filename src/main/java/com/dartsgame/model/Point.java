package com.dartsgame.model;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull
    private Integer throwValue;

    private Integer round;

    public Point() {
    }

    public Point(Player player, Integer throwValue) {
        this.player = player;
        this.throwValue = throwValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public void setThrowValue(Integer throwValue, Integer round) {
        this.throwValue = throwValue;
        this.round = round;
    }
}
