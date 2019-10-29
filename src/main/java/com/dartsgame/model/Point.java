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

    private Integer throw1;

    private Integer throw2;

    private Integer throw3;

    public Point() {
    }

    public Point(Player player, Integer throw1, Integer throw2, Integer throw3) {
        this.player = player;
        this.throw1 = throw1;
        this.throw2 = throw2;
        this.throw3 = throw3;
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

    public Integer getThrow1() {
        return throw1;
    }

    public void setThrow1(Integer throw1) {
        this.throw1 = throw1;
    }

    public Integer getThrow2() {
        return throw2;
    }

    public void setThrow2(Integer throw2) {
        this.throw2 = throw2;
    }

    public Integer getThrow3() {
        return throw3;
    }

    public void setThrow3(Integer throw3) {
        this.throw3 = throw3;
    }
}
