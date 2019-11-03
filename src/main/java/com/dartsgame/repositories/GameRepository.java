package com.dartsgame.repositories;

import com.dartsgame.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    Game findTopById(int id);



}
