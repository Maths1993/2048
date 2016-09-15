package com.example.dominique.a2048;

public class SwipeHandler {

    // Directions
    private final static int DOWN = 0;
    private final static int UP = 1;
    private final static int RIGHT = 2;
    private final static int LEFT = 3;
    private final static int NOWHERE = 4;

    // Minimal distances
    private float xMinDistance = 100;
    private float yMinDistance = 100;
    // Acceptable range
    private float xAccept = 200;
    private float yAccept = 200;

    private float xDown;
    private float yDown;
    private float xUp;
    private float yUp;
    private int factor;

    public SwipeHandler(float xDown, float yDown, float xUp, float yUp, int factor) {
        this.xDown = xDown;
        this.yDown = yDown;
        this.xUp = xUp;
        this.yUp = yUp;
        this.factor = factor;
    }

    public void setXDown(float xDown) { this.xDown = xDown; }
    public void setYDown(float yDown) { this.yDown = yDown; }
    public void setXUp(float xUp) { this.xUp = xUp; }
    public void setYUp(float yUp) { this.yUp = yUp; }

    public float getXDown() { return this.xDown; }
    public float getYDown() { return this.yDown; }
    public float getXUp() { return this.xUp; }
    public float getYUp() { return this.yUp; }

    public int getSwipeDirection(float xDown, float yDown, float xUp, float yUp) {
        float xDistance = xUp - xDown;
        float yDistance = yUp - yDown;
        if(xDistance > xMinDistance && yDistance >= -yAccept && yDistance <= yAccept) return RIGHT;
        else if(xDistance < -xMinDistance && yDistance >= -yAccept && yDistance <= yAccept) return LEFT;
        else if(xDistance >= -xAccept && xDistance <= xAccept && yDistance > yMinDistance) return DOWN;
        else if(xDistance >= -xAccept && xDistance <= xAccept && yDistance < -yMinDistance) return UP;
        else return NOWHERE;
    }

    public int[][] swipe(int swipeDirection, int[][] oldGameField) {

       int[][] gameField = new int[oldGameField.length][oldGameField.length];

        for(int x = 0; x < oldGameField.length; x++) {
            for (int y = 0; y < oldGameField.length; y++) {
                gameField[x][y] = oldGameField[x][y];
            }
        }

        if (swipeDirection == DOWN) {
            for (int x = 0; x < gameField.length; x++) {
                boolean mergeable = false;
                int moveTo = gameField.length - 1;
                int mergeTo = -1;
                for (int y = gameField.length - 1; y > - 1; y--) {
                    int value = gameField[x][y];
                    if (value != 0) {
                        if (!mergeable) {
                            mergeable = true;
                            mergeTo = y;
                        } else {
                            // Merge
                            if(gameField[x][mergeTo] == value) {
                                gameField[x][mergeTo] = 0;
                                gameField[x][y] = 0;
                                gameField[x][moveTo] = factor * value;
                                mergeable = false;
                                moveTo--;
                            } else {
                                gameField[x][moveTo] = gameField[x][mergeTo];
                                if(mergeTo != moveTo) gameField[x][mergeTo] = 0;
                                mergeTo = y;
                                moveTo--;
                            }
                        }
                    }
                    // End state: Possibly move last non-merging block
                    if(y == 0) {
                        if(mergeable) {
                            gameField[x][moveTo] = gameField[x][mergeTo];
                            if(mergeTo != moveTo) gameField[x][mergeTo] = 0;
                        }
                    }
                }
            }
        } else if(swipeDirection == UP) {
            for(int x = 0; x < gameField.length; x++) {
                boolean mergeable = false;
                int moveTo = 0;
                int mergeTo = -1;
                for (int y = 0; y < gameField.length; y++) {
                    int value = gameField[x][y];
                    if(value != 0) {
                        if(!mergeable) {
                            mergeable = true;
                            mergeTo = y;
                        } else {
                            if(gameField[x][mergeTo] == value) {
                                gameField[x][mergeTo] = 0;
                                gameField[x][y] = 0;
                                gameField[x][moveTo] = factor * value;
                                mergeable = false;
                                moveTo++;
                            } else {
                                gameField[x][moveTo] = gameField[x][mergeTo];
                                if(mergeTo != moveTo) gameField[x][mergeTo] = 0;
                                mergeTo = y;
                                moveTo++;
                            }
                        }
                    }
                    // End state: Possibly move last non-merging block
                    if(y == gameField.length - 1) {
                        if(mergeable) {
                            gameField[x][moveTo] = gameField[x][mergeTo];
                           // if(movable) gameField[x][mergeTo] = 0;
                            if(moveTo < mergeTo) gameField[x][mergeTo] = 0;
                        }
                    }
                }
            }
        } else if(swipeDirection == LEFT) {
            for(int y = 0; y < gameField.length; y++) {
                boolean mergeable = false;
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
                                gameField[moveTo][y] = factor * value;
                                mergeable = false;
                                moveTo++;
                            } else {
                                gameField[moveTo][y] = gameField[mergeTo][y];
                                if(moveTo != mergeTo) {
                                    gameField[mergeTo][y] = 0;
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
        } else if(swipeDirection == RIGHT) {
            for(int y = 0; y < gameField.length; y++) {
                boolean mergeable = false;
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
                                gameField[moveTo][y] = factor * value;
                                mergeable = false;
                                moveTo--;
                            } else {
                                gameField[moveTo][y] = gameField[mergeTo][y];
                                if(moveTo != mergeTo) gameField[mergeTo][y] = 0;
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
            }
        }
        return gameField;
    }

}
