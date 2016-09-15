package com.example.dominique.a2048;

public class Parameters {

    private int maxNumber;
    private int fieldsize;
    private int factor;

    public Parameters(int maxNumber, int fieldSize, int factor) {
        this.maxNumber = maxNumber;
        this.fieldsize = fieldSize;
        this.factor = factor;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void setFieldsize(int fieldsize) {
        this.fieldsize = fieldsize;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public int getMaxNumber() {
        return this.maxNumber;
    }

    public int getFieldsize() {
        return this.fieldsize;
    }

    public int getFactor() {
        return this.factor;
    }

}
