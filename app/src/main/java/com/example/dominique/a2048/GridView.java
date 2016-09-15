package com.example.dominique.a2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GridView extends View {

    private Rect field;
    private float fieldLength;
    private float boxLength;
    private int[][] gameField;
    private boolean victory;
    private boolean loss;

    public GridView(Context context, int[][] gameField) {
        super(context);
        this.field = new Rect();
        this.gameField = gameField;
        this.victory = false;
        this.loss = false;
    }

    public void setLoss(boolean value) {
        loss = value;
    }

    public void setVictory(boolean value) {
        victory = value;
    }

    public void updateGameField(int[][] oldGameField) {
        int length = oldGameField.length;
        for (int xPos = 0; xPos < length; xPos++) {
            for (int yPos = 0; yPos < length; yPos++) {
                gameField[xPos][yPos] = oldGameField[xPos][yPos];
            }
        }
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeight, int oldWidth, int oldHeight) {
        super.onSizeChanged(newWidth, newHeight, oldHeight, oldWidth);
        fieldLength = Math.min(newWidth, newHeight);
        boxLength = fieldLength / gameField.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the background
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.puzzle_background));

        int distToLeft = (int) (getWidth() - fieldLength) / 2;
        int distToTop = (int) (getHeight() - fieldLength) / 2;
        canvas.drawRect(distToLeft, distToTop, getWidth() - distToLeft, getHeight() - distToTop, background);

        Paint colorLines = new Paint();
        colorLines.setColor(getResources().getColor(R.color.grid_lines));

        // Draw grid lines
        for (int i = 0; i <= gameField.length; i++) {
            // Horizontal
            canvas.drawLine(distToLeft, distToTop + i * boxLength,
                    distToLeft + fieldLength, distToTop + i * boxLength, colorLines);
            // Vertical
            canvas.drawLine(distToLeft + i * boxLength, distToTop,
                    distToLeft + i * boxLength, distToTop + fieldLength, colorLines);
        }

        Paint colorNumbers = new Paint();
        colorLines.setColor(getResources().getColor(R.color.grid_numbers));
        colorNumbers.setStyle(Paint.Style.FILL);
        colorNumbers.setTextSize(boxLength * 0.4f);
        colorNumbers.setTextScaleX(1);
        colorNumbers.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = colorNumbers.getFontMetrics();

        // Draw numbers into boxes
        for (int i = 0; i <= gameField.length - 1; i++) {
            for (int j = 0; j <= gameField.length - 1; j++) {
                int value = gameField[i][j];
                if(value != 0) {
                    if(value == 2) colorNumbers.setColor(getResources().getColor(R.color.grid_2));
                    if(value == 4) colorNumbers.setColor(getResources().getColor(R.color.grid_4));
                    if(value == 8) colorNumbers.setColor(getResources().getColor(R.color.grid_8));
                    if(value == 16) colorNumbers.setColor(getResources().getColor(R.color.grid_16));
                    if(value == 32) colorNumbers.setColor(getResources().getColor(R.color.grid_32));
                    if(value == 64) colorNumbers.setColor(getResources().getColor(R.color.grid_64));
                    if(value == 128) colorNumbers.setColor(getResources().getColor(R.color.grid_128));
                    if(value == 256) colorNumbers.setColor(getResources().getColor(R.color.grid_256));
                    if(value == 512) colorNumbers.setColor(getResources().getColor(R.color.grid_512));
                    if(value == 1024) colorNumbers.setColor(getResources().getColor(R.color.grid_1024));
                    if(value == 2048) colorNumbers.setColor(getResources().getColor(R.color.grid_2048));
                    if(value == 4096) colorNumbers.setColor(getResources().getColor(R.color.grid_4096));
                    float xPos = distToLeft + boxLength / 2 + i * boxLength;
                    float yPos = distToTop + boxLength / 2 - (fm.ascent + fm.descent) / 2 + j * boxLength;
                    canvas.drawText(Integer.toString(value), xPos, yPos, colorNumbers);
                }
            }
        }

        Paint messageFont = new Paint();
        messageFont.setColor(getResources().getColor(R.color.grid_message));
        messageFont.setStyle(Paint.Style.FILL);
        messageFont.setTextSize(boxLength * 0.5f);
        messageFont.setTextScaleX(1);
        messageFont.setTextAlign(Paint.Align.CENTER);
        //Paint.FontMetrics fm = colorNumbers.getFontMetrics();

        Paint colorGridBackground =  new Paint();
        colorGridBackground.setColor(getResources().getColor(R.color.grid_background));
        canvas.drawRect(field, colorGridBackground);

        if(loss) canvas.drawText("Nächst mol", getWidth() / 2, getHeight() / 2, messageFont);
        else if(victory) canvas.drawText("Des gibt a pfetzer!", getWidth() / 2, getHeight() / 2, messageFont);
    }

}
