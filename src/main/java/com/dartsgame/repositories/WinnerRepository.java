package com.dartsgame.repositories;

import com.dartsgame.model.Winner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinnerRepository extends CrudRepository<Winner, Integer> {


    List<Winner> findAllBy();

    List<Winner> findTop1ByOrderByIdDesc();

    List<Winner> findTop5ByOrderByIdDesc();

    List<Winner> findTop10ByOrderByIdDesc();

    List<Winner> findTop15ByOrderByIdDesc();


}
