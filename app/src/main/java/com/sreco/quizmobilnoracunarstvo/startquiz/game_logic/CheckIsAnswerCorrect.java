package com.sreco.quizmobilnoracunarstvo.startquiz.game_logic;

import java.util.List;

public class CheckIsAnswerCorrect {
    //Metoda prima moguce odgovore, trenutnu liniju u fajlu i tacne odgovore iz fajla i na osnovu toga racuna da li je korisnik dobro odgovorio.
    public boolean checkIsAnswerCorrect(List<Integer> possibleResult, int currentLineInFile, List<Integer> resultsOCurrentQuiz){
        for(int value : possibleResult){
            if(value > currentLineInFile - 5 && value <= currentLineInFile){
                for(int valueOfResults : resultsOCurrentQuiz){
                    if(valueOfResults > currentLineInFile - 5 && valueOfResults <= currentLineInFile){
                        if(value == valueOfResults)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}
