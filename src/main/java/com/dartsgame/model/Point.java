package com.dartsgame.model;

import javax.persistence.*;

@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Integer throwValue;

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
}
