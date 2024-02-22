package com.example.hw_2_pizza_order.entities;

public enum PizzaRecipe {
    HAWAII_PIZZA(187),
    CARBONARA_PIZZA(176),
    HUNTERS_PIZZA(165);

    private int cost;

    PizzaRecipe(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
