package com.company.models;

import com.company.common.Constants;
import com.company.exceptions.Fibo2048Exception;
import com.company.services.impl.GameOperationsImpl;

public class Fibo2048Game {
    private String  [][]gameArea = new String[Constants.GAME_ROWS][Constants.GAME_COLUMNS];

    public Fibo2048Game(String[][] gameArea) {
        this.gameArea = gameArea;
    }

    public Fibo2048Game() throws Fibo2048Exception {
        for(int row = 0;row<Constants.GAME_ROWS;row++){
            for(int col = 0;col<Constants.GAME_COLUMNS;col++){
                gameArea[row][col] = Constants.EMPTY_CELL;
            }
        }
        gameArea = GameOperationsImpl.fillRandomEmptyCell(gameArea);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String[] row:gameArea){
            for(String cell:row){
                sb.append(cell).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String[][] getGameArea() {
        return gameArea;
    }

    public void setGameArea(String[][] gameArea) {
        this.gameArea = gameArea;
    }
}
