package com.example.dominique.a2048;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameFieldManager {

    private int gameField[][];
    private int factor;
    private int maxNumber;
  //  private final static int MAX = 1024;

    public GameFieldManager(int fieldSize, int factor, int maxNumber) {
        this.factor = factor;
        this.maxNumber = maxNumber;
        this.gameField = new int[fieldSize][fieldSize];
        // Initialization with zeros
        for (int xPos = 0; xPos < fieldSize; xPos++) {
            for (int yPos = 0; yPos < fieldSize; yPos++) {
                gameField[xPos][yPos] = 0;
            }
        }
    }

    public void insertValue(int value, int xPos, int yPos) { gameField[xPos][yPos] = value; }

    public int[][] getGameField() {
        int length = gameField.length;
        int[][] newGameField = new int[length][length];
        for (int xPos = 0; xPos < length; xPos++) {
            for (int yPos = 0; yPos < length; yPos++) {
                newGameField[xPos][yPos] = gameField[xPos][yPos];
            }
        }
        return newGameField;
    }

    public void updateGameField(int[][] oldGameField) {
        int length = oldGameField.length;
        for (int xPos = 0; xPos < length; xPos++) {
            for (int yPos = 0; yPos < length; yPos++) {
                gameField[xPos][yPos] = oldGameField[xPos][yPos];
            }
        }
    }

    public void placeRandomBlock() {
        ArrayList<int[][]> values = new ArrayList<>(0);
        for(int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField.length; y++) {
                if(gameField[x][y] == 0) {
                    int[][] xyValues = new int[1][2];
                    xyValues[0][0] = x;
                    xyValues[0][1] = y;
                    values.add(xyValues);
                }
            }
        }
        if(values.size() > 0) {
            // Value of new box
            int value = factor;
            // Generate random integer between 0 (inclusive) and #elements in values array (exclusive)
            Random r = new Random();
            int lower = 0;
            int upper = values.size() - 1;
            int rand = (lower != upper) ? r.nextInt(upper - lower) + lower : 0;
            int randX = values.get(rand)[0][0];
            int randY = values.get(rand)[0][1];
            Log.w("randX", Integer.toString(randX));
            Log.w("randY", Integer.toString(randY));
            // Insert value
            insertValue(value, randX, randY);
        }
    }

    public boolean existChanges(int[][] oldGameField, int[][] newGameField) {
        for(int x = 0; x < oldGameField.length; x++) {
            for (int y = 0; y < oldGameField.length; y++) {
                if(oldGameField[x][y] != newGameField[x][y]) return true;
            }
        }
        return false;
    }

    public boolean isWin(int[][] gameField) {
        for(int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField.length; y++) {
                if(gameField[x][y] == maxNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean gameFieldFull(int[][] gameField) {
        for(int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField.length; y++) {
                Log.w("SALLE", "here i am");
                if(gameField[x][y] == 0) {
                    Log.w("VAL", Integer.toString(gameField[x][y]));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLoss(int[][] gameField, SwipeHandler swipeHandler) {
        if(gameFieldFull(gameField)) {
            Log.w("TAG", "FULL!!");
            // Try down swipe
            int[][] gameFieldDown = swipeHandler.swipe(0, gameField);
            if(!existChanges(gameField, gameFieldDown)) {
                // Try up swipe
                int[][] gameFieldUp = swipeHandler.swipe(1, gameField);
                if(!existChanges(gameField, gameFieldUp)) {
                    // Try right swipe
                    int[][] gameFieldRight = swipeHandler.swipe(2, gameField);
                    if(!existChanges(gameField, gameFieldRight)) {Log.w("Here i am", "111");
                        // Try left swipe
                        int[][] gameFieldLeft = swipeHandler.swipe(3, gameField);
                        if(!existChanges(gameField, gameFieldLeft)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
