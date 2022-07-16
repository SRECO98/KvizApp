package com.sreco.quizmobilnoracunarstvo.startquiz.game_logic;

import java.util.ArrayList;
import java.util.List;

public class LogicToFindAnswers {
    int brojac = 0;
    String zvjezdice = "*****";
    public ArrayList<Integer> findAnswers(List<String> list){
        ArrayList<Integer> resultList = new ArrayList<>();
        while(brojac < list.size()){
            if(list.get(brojac).startsWith(zvjezdice)){
                resultList.add(brojac);
            }
            brojac++;
        }
        return resultList;
    }
}
