package com.example.hw_2_pizza_order;

import android.graphics.drawable.Drawable;

public class Pizza {
    private int id, Size, Weight, Price;
    private String Name, Recipe;
    private int Picture;

    public Pizza(int id, int size, int weight, int price, String name, String recipe, int picture) {
        this.id = id;
        Size = size;
        Weight = weight;
        Price = price;
        Name = name;
        Recipe = recipe;
        Picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRecipe() {
        return Recipe;
    }

    public void setRecipe(String recipe) {
        Recipe = recipe;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }
}
