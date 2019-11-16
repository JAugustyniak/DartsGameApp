package com.dartsgame.services;

import com.dartsgame.model.Winner;
import com.dartsgame.repositories.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerService {

    WinnerRepository winnerRepository;

    @Autowired
    public WinnerService(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    public void saveWinner(Winner winner){
        winnerRepository.save(winner);
    }

    public List<Winner> getAllWinners(){
        return winnerRepository.findAllByOrderByIdDesc();
    }



    public List<Winner> getNumberOfLastWinners(Integer number){
        if(number==1){

            return winnerRepository.findTop1ByOrderByIdDesc();
        }
        else if(number==5){

            return winnerRepository.findTop5ByOrderByIdDesc();

        }else if(number==10){

            return winnerRepository.findTop10ByOrderByIdDesc();
        }
        else {

            return winnerRepository.findTop15ByOrderByIdDesc();
        }

    }

}
