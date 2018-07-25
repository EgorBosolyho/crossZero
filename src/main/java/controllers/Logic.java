package controllers;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Logic {
    private final ArrayList<String> winList = new ArrayList<>(Arrays.asList("123", "456", "789", "147", "258", "369", "159", "357"));
    private List<String> listX;
    private List<String> listO;
    Map<String, String> fieldMap;

    public Logic() {
        resetField();
    }

    void resetField() {
        fieldMap = new HashMap<>();
        listX = new ArrayList<>();
        listO = new ArrayList<>();
    }

    void actionPC(String userAction) {
        fieldMap.put(userAction, "x");
        listX.add(userAction);
        //проверка поля на свободные ячейки(есть ли куда походить ПК)
        if (fieldMap.size() != 9) {
            //если интеллект не смог подобрать хороший вариант - зарандомить
            String actionPC;
            if (!checkWinAction(listO).equals("")) {
                actionPC = checkWinAction(listO);
            } else {
                actionPC = checkWinAction(listX).equals("") ? randomCell() : checkWinAction(listX);
            }
            fieldMap.put(actionPC, "o");
            listO.add(actionPC);
        }
    }

    private String randomCell() {
        while (true) {
            //случайное число от 1 до 9
            int key = ThreadLocalRandom.current().nextInt(1, 10);
            String randomKey = Integer.toString(key);
            //если такая ячейка не занята - заполнить её
            if (!fieldMap.containsKey(randomKey)) {
                return randomKey;
            }
        }
    }

    String checkEndGame() {
        //проверка окончания игры
        if (checkWinList(listX)) {
            return "Победа! Вы выиграли.";
        }
        if (checkWinList(listO)) {
            return "Поражение! Победил компьютер.";
        }
        if (fieldMap.size() == 9) {
            return "Ничья";
        } else return "";
    }

    private boolean checkWinList(List<String> list) {
        //проверка на выигрышные сочетания ячеек
        for (String s : winList) {
            int allCells = 0;
            for (String ss : list) {
                if (s.contains(ss)) {
                    allCells = allCells + 1;
                }
            }
            if (allCells == 3) {
                return true;
            }
        }
        return false;
    }

    private String checkWinAction(List<String> list) {
        //проверка на выигрышные сочетания ячеек
        if (!listX.contains("5") && !listO.contains("5")) {
            return "5";
        }
        for (String s : winList) {
            int setCells = 0;
            int freeCells = 0;
            String freeCellNumber = "";

            char[] chars = s.toCharArray();
            for (char sChar : chars) {

                String stringChar = Character.toString(sChar);
                if (list.contains(stringChar)) {
                    setCells = setCells + 1;
                } else {
                    if (!fieldMap.containsKey(stringChar)) {
                        freeCells = freeCells + 1;
                        freeCellNumber = stringChar;
                    }
                }
            }
            if (setCells == 2 && freeCells == 1) {
                return freeCellNumber;
            }
        }
        return "";
    }


}
