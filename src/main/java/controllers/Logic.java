package controllers;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Logic {
    static ArrayList<String> winList = new ArrayList<>(Arrays.asList("123", "456", "789", "147", "258", "369", "159", "357"));
    static List<String> listX = new ArrayList<>();
    static List<String> listO = new ArrayList<>();

    public static String actionPC(Map<String,String> map){
        //обнуление прошлого
        listX = new ArrayList<>();
        listO = new ArrayList<>();
        //Разбитие поля на ячейки в которые походил пользователь и ПК
        for(Map.Entry<String,String> pair: map.entrySet()){
            String value = pair.getValue();
            String key = pair.getKey();
            if(value.equals("x")){
                listX.add(key);
            }
            if(value.equals("o")){
                listO.add(key);
            }
        }

        String checkWinPC = checkWinPC(map);
            if(!checkWin(listX)) {
                //если интеллект не смог подобрать хороший вариант - зарандомить
                String randomKey;
                if (checkWinPC.equals("")) {
                    //проверка поля на свободные ячейки(есть ли куда походить ПК)
                    if (map.size() != 9) {
                        do {
                            //генерация случайного числа в диапозоне от 1 до 9
                            int key = ThreadLocalRandom.current().nextInt(1, 10);
                            randomKey = Integer.toString(key);
                        } while (map.containsKey(randomKey));
                        //если такая ячейка не занята - заполнить её
                        map.put(randomKey,"o");
                    }
                } else {
                    //если интеллект нашёл хорошее решение - записать его
                    map.put(checkWinPC, "o");
                    listO.add(checkWinPC);
                }
            }
        //проверка окончания игры или победы пользователя или ПК

        if(checkWin(listX)){
            return "Победа! Вы выиграли.";
        }
        if (checkWin(listO)){
            return "Поражение! Победил компьютер.";
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

    private static String checkWinPC(Map<String,String> map){
        //проверка на выигрышные сочетания ячеек
        String best2 = "";
        String best3 = "";
        if (!listX.contains("5") && !listO.contains("5")){
            return "5";
        }

        for(String s: winList){
            int setCells = 0;
            int freeCells = 0;
            String freeCellNumber = "";

            char[] chars = s.toCharArray();
            for(char sChar: chars){

                String stringChar = Character.toString(sChar);
                if(listO.contains(stringChar)){
                    setCells=setCells+1;
                } else {
                    if(!listX.contains(stringChar)){
                        freeCells=freeCells+1;
                        freeCellNumber = stringChar;
                    }
                }
            }
            if(setCells==2 && freeCells==1){
                return freeCellNumber;
            } else if(setCells==1 && freeCells==2){
                best2 = freeCellNumber;
            } else if(freeCells==3){
                best3 = freeCellNumber;
            }
        }

        if(best2.equals("")){
            return best3;
        } else return best2;
    }

}
