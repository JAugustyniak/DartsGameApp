package com.dartsgame.services;


import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.model.Point;
import com.dartsgame.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    PointRepository pointRepository;

    @Autowired
    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public List<Point> findAllByPlayerId(Integer id){
        return pointRepository.findAllByPlayerId(id);
    }

    public void savePoint(Point point){
        pointRepository.save(point);
    }

    public Integer getSumPoints(Player player){
        return pointRepository.getSumPoints(player);
    }

    public List<Point> getPointByGameId(Game game){
        return pointRepository.getPointByGameIdOrOrderByRoundAndId(game);
    }
}
