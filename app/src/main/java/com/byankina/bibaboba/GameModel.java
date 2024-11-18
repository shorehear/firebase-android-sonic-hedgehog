package com.byankina.bibaboba;

public class GameModel {
    private String name;
    private String price;
    private String date;
    private String score;
    private int imageResource;

    public GameModel(String name, String price, String date, String score, int imageResource) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.score = score;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }

    public int getImageResource() {
        return imageResource;
    }
}