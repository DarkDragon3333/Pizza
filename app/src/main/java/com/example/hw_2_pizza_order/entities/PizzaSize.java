package com.example.hw_2_pizza_order.entities;

public enum PizzaSize {
    LARGE(60, 80.0),
    MEDIUM(40, 40.0),
    SMALL(20, 0);

    private int sizeSm;
    private double margin;

    PizzaSize(int sizeSm, double margin) {
        this.sizeSm = sizeSm;
        this.margin = margin;
    }

    public int getSizeSm() {
        return sizeSm;
    }

    public double getMargin() {
        return margin;
    }
}
