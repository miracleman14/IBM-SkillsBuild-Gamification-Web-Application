package org.example.ibmskillsbuildapp.model;

public class Rating {

    private int value;

    public Rating() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isValid() {
        return value >= 1 && value <= 5;
    }
}
