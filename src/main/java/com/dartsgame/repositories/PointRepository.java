package com.dartsgame.repositories;

import com.dartsgame.model.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends CrudRepository<Point, Integer> {
//
//    @Query(value = "Select * from points where player_id in (Select id from player where game_id=?1)", nativeQuery = true)

//    @Query(value = "Select p from Point p where p.player in (Select pl.id from  where pl.game_id=?1)")
//    List<Point> getByGameID(Integer gameId);


//    @Query("Select pl from Player ")




}
