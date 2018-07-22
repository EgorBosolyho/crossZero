package controllers;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Logic {
    static ArrayList<String> winList = new ArrayList<>(Arrays.asList("123", "456", "789", "147", "258", "369", "159", "357"));
    public static String actionPC(Map<String,String> map){
        String keyString;
        //проверка поля на свободные ячейки(есть ли куда походить ПК)
        if(map.size()!=9) {
            do {
                //генерация случайного числа в диапозоне от 1 до 9
                int key = ThreadLocalRandom.current().nextInt(1, 10);
                keyString = Integer.toString(key);
            } while (map.containsKey(keyString));
            //если такая ячейка не занята - заполнить её
            map.put(keyString, "o");
        }
        //проверка окончания игры или победы пользователя или ПК
        return checkWin(map);
    }

    public static String checkWin(Map<String,String> map){
        //Разбитие поля на ячейки в которые походил пользователь и ПК
        List<String> listX = new ArrayList<>();
        List<String> listO = new ArrayList<>();

        for(Map.Entry<String,String> pair: map.entrySet()){
            String value = pair.getValue();
            String key = pair.getKey();
            if(value.equals("x")){
                listX.add(key);
            }
            if(value.equals("o")){
                listO.add(key);
            }
            if(checkWin(listX)){
                return "Победа! Вы выиграли.";
            }
            if (checkWin(listO)){
                return "Поражение! Победил компьютер.";
            }
        }
        if(map.size()==9){
            return "Ничья";
        } else return "";
    }

    public static boolean checkWin(List<String> list){
        //проверка на выигрышные сочетания ячеек
        for(String s: winList){
            int allCells = 0;
            for(String ss: list){
                if(s.contains(ss)){
                    allCells=allCells+1;
                }
            }
            if (allCells==3){
                return true;
            }
        }
        return false;
    }
}
