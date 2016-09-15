package com.example.dominique.a2048;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private SwipeHandler swipeHandler;
    private GridView gridView;
    private GameFieldManager gameFieldManager;
    private Parameters parameters;

    private static final int FIELDSIZE_3x3_ID = 300;
    private static final int FIELDSIZE_4x4_ID = 400;
    private static final int FIELDSIZE_5x5_ID = 500;
    private static final int FIELDSIZE_6x6_ID = 600;

    private static final int FACTOR_2_ID = 700;
    private static final int FACTOR_4_ID = 800;
    private static final int FACTOR_8_ID = 900;
    private static final int FACTOR_16_ID = 1000;

    private static final int MAX_512_ID = 1100;
    private static final int MAX_1024_ID = 1200;
    private static final int MAX_2048_ID = 1300;
    private static final int MAX_4096_ID = 1400;

    private boolean gameOver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Default values for game field
        int maxNumber = 512;
        int fieldLength = 4;
        int factor = 2;

        gameOver = false;

        parameters = new Parameters(maxNumber, fieldLength, factor);

        startGame();
    }

    public void startGame() {
        gameFieldManager = new GameFieldManager(
                parameters.getFieldsize(),
                parameters.getFactor(),
                parameters.getMaxNumber()
        );
        gameFieldManager.placeRandomBlock();

        int[][] gF = gameFieldManager.getGameField();
        gridView = new GridView(this, gF);
        setContentView(gridView);

        float xDown = 0;
        float yDown = 0;
        float xUp = 0;
        float yUp = 0;
        swipeHandler = new SwipeHandler(xDown, yDown, xUp, yUp, parameters.getFactor());

        gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent touchEvent) {
                 if(!gameOver) {
                if (touchEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    swipeHandler.setXDown(touchEvent.getX());
                    swipeHandler.setYDown(touchEvent.getY());
                } else if (touchEvent.getAction() == MotionEvent.ACTION_UP) {
                    swipeHandler.setXUp(touchEvent.getX());
                    swipeHandler.setYUp(touchEvent.getY());
                    int swipeDirection = swipeHandler.getSwipeDirection(
                            swipeHandler.getXDown(), swipeHandler.getYDown(),
                            swipeHandler.getXUp(), swipeHandler.getYUp()
                    );
                    if (swipeDirection != 4) {
                        int[][] oldGameField = gameFieldManager.getGameField();
                        int[][] newGameField = swipeHandler.swipe(swipeDirection, oldGameField);

                        gameFieldManager.updateGameField(newGameField);
                        gridView.updateGameField(newGameField);

                        if (gameFieldManager.existChanges(oldGameField, newGameField)) {
                            gameFieldManager.placeRandomBlock();
                        }

                        int[][] randomizedGameField = gameFieldManager.getGameField();

                        if(gameFieldManager.isWin(randomizedGameField)) {
                            gridView.setVictory(true);
                            gameOver = true;
                        }
                        else if(gameFieldManager.isLoss(randomizedGameField, swipeHandler)) {
                            gridView.setLoss(true);
                            gameOver = true;
                        }

                        int[][] updatedGameField = gameFieldManager.getGameField();
                        gameFieldManager.updateGameField(updatedGameField);
                        gridView.updateGameField(updatedGameField);

                        gridView.invalidate();
                    }
                }
            } return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subFieldSize = menu.addSubMenu(0, 0, 1, "Feldgröße");
        subFieldSize.add(0, FIELDSIZE_3x3_ID, Menu.NONE, "3x3");
        subFieldSize.add(0, FIELDSIZE_4x4_ID, Menu.NONE, "4x4");
        subFieldSize.add(0, FIELDSIZE_5x5_ID, Menu.NONE, "5x5");
        subFieldSize.add(0, FIELDSIZE_6x6_ID, Menu.NONE, "6x6");
/*
        SubMenu subFactor = menu.addSubMenu(0, 1, 1, "Vervielfachungsfaktor");
        subFactor.add(0, FACTOR_2_ID, Menu.NONE, "2");
        subFactor.add(0, FACTOR_4_ID, Menu.NONE, "4");
        subFactor.add(0, FACTOR_8_ID, Menu.NONE, "8");
        subFactor.add(0, FACTOR_16_ID, Menu.NONE, "16");
        */

        SubMenu subMaxNumber = menu.addSubMenu(0, 2, 1, "Höchste Gewinnzahl");
        subMaxNumber.add(0, MAX_512_ID, Menu.NONE, "512");
        subMaxNumber.add(0, MAX_1024_ID, Menu.NONE, "1024");
        subMaxNumber.add(0, MAX_2048_ID, Menu.NONE, "2048");
        subMaxNumber.add(0, MAX_4096_ID, Menu.NONE, "4096");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        gameOver = false;
        switch (item.getItemId()) {
            case FIELDSIZE_3x3_ID:
                parameters.setFieldsize(3);
                startGame();
                return true;
            case FIELDSIZE_4x4_ID:
                parameters.setFieldsize(4);
                startGame();
                return true;
            case FIELDSIZE_5x5_ID:
                parameters.setFieldsize(5);
                startGame();
                return true;
            case FIELDSIZE_6x6_ID:
                parameters.setFieldsize(6);
                startGame();
                return true;
            case FACTOR_2_ID:
                parameters.setFactor(2);
                startGame();
                return true;
            case FACTOR_4_ID:
                parameters.setFactor(4);
                startGame();
                return true;
            case FACTOR_8_ID:
                parameters.setFactor(8);
                startGame();
                return true;
            case FACTOR_16_ID:
                parameters.setFactor(16);
                startGame();
                return true;
            case MAX_512_ID:
                parameters.setMaxNumber(512);
                startGame();
                return true;
            case MAX_1024_ID:
                parameters.setMaxNumber(1024);
                startGame();
                return true;
            case MAX_2048_ID:
                parameters.setMaxNumber(2048);
                startGame();
                return true;
            case MAX_4096_ID:
                parameters.setMaxNumber(4096);
                startGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}