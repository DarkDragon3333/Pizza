package com.example.hw_2_pizza_order.entities;

public enum PizzaTopping {
    MEAT(12),
    MUSHROOMS(10),
    CHEESE(8);

    private int cost;

    PizzaTopping(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
