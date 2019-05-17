package com.company.services.impl;

import com.company.common.Constants;
import com.company.common.Direction;
import com.company.common.GameUtil;
import com.company.exceptions.Fibo2048Exception;
import com.company.models.Fibo2048Game;
import com.company.services.GameOperations;

public class GameOperationsImpl implements GameOperations {

    private Fibo2048Game game = new Fibo2048Game();
    private GameUtil gameUtil = new GameUtil();

    public GameOperationsImpl() throws Fibo2048Exception {
        System.out.println(game.toString());
    }


    private int getNextNonEmpty(int currPos, String[] line) {
        if (currPos >= line.length) {
            return line.length;
        }
        for (int nextPos = currPos + 1; nextPos < line.length; nextPos++) {
            if (!Constants.EMPTY_CELL.equals(line[nextPos])) {
                return nextPos;
            }
        }
        return line.length;
    }

    private long add(String num1, String num2) {
        long n1 = Long.valueOf(num1);
        long n2 = Long.valueOf(num2);
        return n1 + n2;
    }

    public String[] collapseLine(String[] line) throws Fibo2048Exception {
        int fillingPos = 0;
        for (int currPos = getNextNonEmpty(-1, line);
             currPos < line.length;
             currPos = getNextNonEmpty(currPos, line),fillingPos++) {

            int nextPos = getNextNonEmpty(currPos, line);
            if (nextPos < line.length) {
                long sum = add(line[currPos], line[nextPos]);
                if (gameUtil.isFibonacci(sum)) {
                    gameUtil.userWonCheck(sum);
                    line[currPos] = Constants.EMPTY_CELL;
                    line[fillingPos] = String.valueOf(sum);
                    line[nextPos] = Constants.EMPTY_CELL;
                }
                else{
                    if (currPos != fillingPos) {
                        line[fillingPos] = line[currPos];
                        line[currPos] = Constants.EMPTY_CELL;
                    }
                }
            } else {
                if (currPos != fillingPos) {
                    line[fillingPos] = line[currPos];
                    line[currPos] = Constants.EMPTY_CELL;
                }
            }
        }

        return line;
    }

    public static String[][] fillRandomEmptyCell(String[][] gameArea) throws Fibo2048Exception {
        int emptyCellsCount = 0;
        for (String[] row : gameArea) {
            for (String cell : row) {
                if (cell.equals(Constants.EMPTY_CELL)) {
                    emptyCellsCount++;
                }
            }
        }
        if (emptyCellsCount == 0) {
            throw new Fibo2048Exception("Game Over! Try Again");
        }
        int random = (int) (Math.random() * emptyCellsCount + 0.5);
        for (int row = 0; row < Constants.GAME_ROWS; row++) {
            for (int col = 0; col < Constants.GAME_COLUMNS; col++) {

                if (gameArea[row][col].equals(Constants.EMPTY_CELL)) {
                    if (random == 0) {
                        gameArea[row][col] = "1";
                    }
                    random--;
                }
            }
        }
        return gameArea;
    }

    @Override
    public void move(Direction direction) throws Fibo2048Exception {
        String[][] gameArea = game.getGameArea();
        switch (direction) {
            case DOWN:
                for (int col = 0; col < Constants.GAME_COLUMNS; col++) {
                    String[] colLine = new String[Constants.GAME_ROWS];
                    for (int row = Constants.GAME_ROWS; row > 0; row--) {
                        colLine[Constants.GAME_ROWS - row] = gameArea[row - 1][col];
                    }
                    colLine = collapseLine(colLine);
                    for (int row = Constants.GAME_ROWS; row > 0; row--) {
                        gameArea[row - 1][col] = colLine[Constants.GAME_ROWS - row];
                    }
                }
                break;
            case UP:
                for (int col = 0; col < Constants.GAME_COLUMNS; col++) {
                    String[] colLine = new String[Constants.GAME_ROWS];
                    for (int row = Constants.GAME_ROWS; row > 0; row--) {
                        colLine[row - 1] = gameArea[row - 1][col];
                    }
                    colLine = collapseLine(colLine);
                    for (int row = Constants.GAME_ROWS; row > 0; row--) {
                        gameArea[row - 1][col] = colLine[row - 1];
                    }
                }
                break;
            case LEFT:
                for (int row = 0; row < Constants.GAME_ROWS; row++) {
                    String[] rowLine = new String[Constants.GAME_ROWS];
                    for (int col = Constants.GAME_COLUMNS; col > 0; col--) {
                        rowLine[col - 1] = gameArea[row][col - 1];
                    }
                    rowLine = collapseLine(rowLine);
                    for (int col = Constants.GAME_COLUMNS; col > 0; col--) {
                        gameArea[row][col - 1] = rowLine[col - 1];
                    }
                }
                break;
            case RIGHT:
                for (int row = 0; row < Constants.GAME_ROWS; row++) {
                    String[] rowLine = new String[Constants.GAME_ROWS];
                    for (int col = Constants.GAME_COLUMNS; col > 0; col--) {
                        rowLine[Constants.GAME_COLUMNS - col] = gameArea[row][col - 1];
                    }
                    rowLine = collapseLine(rowLine);
                    for (int col = Constants.GAME_COLUMNS; col > 0; col--) {
                        gameArea[row][col - 1] = rowLine[Constants.GAME_COLUMNS - col];
                    }
                }
                break;
        }
        gameArea = fillRandomEmptyCell(gameArea);
        game.setGameArea(gameArea);
        System.out.println(game);
    }
}
