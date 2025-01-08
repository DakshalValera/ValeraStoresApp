package com.example.valerastores;

public class MainModel {

    String name,price,weight,image;

    public MainModel(String name, String price, String weight, String image) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getWeight() {
        return weight;
    }

    public String getImage() {
        return image;
    }

    public MainModel() {
    }
}
