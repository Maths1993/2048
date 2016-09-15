package com.example.dominique.a2048;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[][] gameField = new int[4][4];
      /*  gameField[0][9] = 1;
        gameField[0][8] = 0;
        gameField[0][7] = 1;
        gameField[0][6] = 1;
        gameField[0][5] = 0;
        gameField[0][4] = 0;*/
        gameField[0][3] = 4;
        gameField[0][2] = 2;
        gameField[0][1] = 0;
        gameField[0][0] = 0;

        // Down
    /*    for(int x = 0; x < gameField.length - 1; x++) {
            boolean mergeable = false;
            int moveTo = gameField.length - 1;
            int mergeTo = -1;
            for (int y = gameField.length - 1; y > - 1; y--) {
                int value = gameField[x][y];
                if(value != 0) {
                    if(!mergeable) {
                        mergeable = true;
                        mergeTo = y;
                    } else {
                        // Merge
                        if(gameField[x][mergeTo] == value) {
                            gameField[x][mergeTo] = 0;
                            gameField[x][y] = 0;
                            gameField[x][moveTo] = 2 * value;
                            mergeable = false;
                            moveTo--;
                        } else {
                            gameField[x][moveTo] = gameField[x][mergeTo];
                            gameField[x][mergeTo] = 0;
                            mergeTo = y;
                            moveTo--;
                        }
                    }
                }
                // End state: Possibly move last non-merging block
                if(y == 0) {
                    if(mergeable) {
                        gameField[x][moveTo] = gameField[x][mergeTo];
                        gameField[x][mergeTo] = 0;
                    }
                }
            }
        }*/

        // Up
        for(int x = 0; x < 1; x++) {
            boolean mergeable = false;
            boolean movable = false;
            int moveTo = 0;
            int mergeTo = -1;
            for (int y = 0; y < gameField.length; y++) {
                int value = gameField[x][y];
                if(value != 0) {
                    if(!mergeable) {
                        mergeable = true;
                        mergeTo = y;
                    } else {
                        // Merge
                        if(gameField[x][mergeTo] == value) {
                            gameField[x][mergeTo] = 0;
                            gameField[x][y] = 0;
                            gameField[x][moveTo] = 2 * value;
                            mergeable = false;
                            movable = true;
                            moveTo++;
                        } else {
                            gameField[x][moveTo] = gameField[x][mergeTo];
                            //if(movable) {
                            if(mergeTo != moveTo) {
                                //  gameField[x][moveTo] = gameField[x][mergeTo];
                                gameField[x][mergeTo] = 0;
                                movable = false;
                            }
                            //}
                            mergeTo = y;
                            moveTo++;
                        }
                    }
                }
                // End state: Possibly move last non-merging block
                if(y == gameField.length - 1) {
                    if(mergeable) {
                        gameField[x][moveTo] = gameField[x][mergeTo];
                        if(moveTo != mergeTo) gameField[x][mergeTo] = 0;
                    }
                }
            }
        }

        for(int x = 0; x < gameField.length; x++) {
            for(int y = 0; y < gameField.length; y++) {
                Log.w("Value", Integer.toString(gameField[x][y]));
            }
        }

     /*   // Right
        for(int y = 0; y < 1; y++) {
            boolean mergeable = false;
            boolean movable = false;
            int moveTo = gameField.length - 1;
            int mergeTo = -1;
            for (int x = gameField.length - 1; x > -1; x--) {
                int value = gameField[x][y];
                if(value != 0) {
                    if(!mergeable) {
                        mergeable = true;
                        mergeTo = x;
                    } else {
                        // Merge
                        if(gameField[mergeTo][y] == value) {
                            gameField[mergeTo][y] = 0;
                            gameField[x][y] = 0;
                            gameField[moveTo][y] = 2 * value;
                            mergeable = false;
                            movable = true;
                            moveTo--;
                        } else {
                            if(movable) {
                                gameField[moveTo][y] = gameField[mergeTo][y];
                                gameField[mergeTo][y] = 0;
                                movable = false;
                            }
                            mergeTo = x;
                            moveTo--;
                        }
                    }
                }
                // End state: Possibly move last non-merging block
                if(x == 0) {
                    if(mergeable) {
                        gameField[moveTo][y] = gameField[mergeTo][y];
                        if(moveTo != mergeTo) gameField[mergeTo][y] = 0;
                    }
                }
            }
        }*/

        // Left
    /*    for(int y = 0; y < 1; y++) {
            boolean mergeable = false;
            boolean movable = false;
            int moveTo = 0;
            int mergeTo = -1;
            for (int x = 0; x < gameField.length; x++) {
                int value = gameField[x][y];
                if(value != 0) {
                    if(!mergeable) {
                        mergeable = true;
                        mergeTo = x;
                    } else {
                        // Merge
                        if(gameField[mergeTo][y] == value) {
                            gameField[mergeTo][y] = 0;
                            gameField[x][y] = 0;
                            gameField[moveTo][y] = 2 * value;
                            mergeable = false;
                            movable = true;
                            moveTo++;
                        } else {
                            if(movable) {
                                gameField[moveTo][y] = gameField[mergeTo][y];
                                gameField[mergeTo][y] = 0;
                                movable = false;
                            }
                            mergeTo = x;
                            moveTo++;
                        }
                    }
                }
                // End state: Possibly move last non-merging block
                if(x == gameField.length - 1) {
                    if(mergeable) {
                        gameField[moveTo][y] = gameField[mergeTo][y];
                        if(moveTo != mergeTo) gameField[mergeTo][y] = 0;
                    }
                }
            }
        }
*/

    }

}