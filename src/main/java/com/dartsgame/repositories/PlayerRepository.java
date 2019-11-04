package com.dartsgame.repositories;

import com.dartsgame.model.Game;
import com.dartsgame.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    List<Player> findAllByGame(Game game);
    Player findPlayerById(Integer id);

}
