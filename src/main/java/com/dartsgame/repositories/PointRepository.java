package com.dartsgame.repositories;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import com.dartsgame.model.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends CrudRepository<Point, Integer> {

    List<Point> findAllByPlayerId(Integer id);

    @Query("Select sum(p.throwValue) from Point p where p.player = ?1")
    Integer getSumPoints(Player player);

    @Query("SELECT p FROM Point p LEFT JOIN  p.player pl on p.player=pl.id where pl.game=?1 order by p.round, p.player.id, p.throwNumber asc")
    List<Point> getPointByGameIdOrOrderByRoundAndId(Game game);






}
