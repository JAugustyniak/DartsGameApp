package com.dartsgame.repositories;

import com.dartsgame.model.Winner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinnerRepository extends CrudRepository<Winner, Integer> {


    List<Winner> findAllBy();
}
