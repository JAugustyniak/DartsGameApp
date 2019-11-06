package com.dartsgame.services;

import com.dartsgame.model.Winner;
import com.dartsgame.repositories.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerService {

    @Autowired
    WinnerRepository winnerRepository;

    public void saveWinner(Winner winner){
        winnerRepository.save(winner);
    }

    public List<Winner> getAllWinners(){
        return winnerRepository.findAllBy();
    }

}
