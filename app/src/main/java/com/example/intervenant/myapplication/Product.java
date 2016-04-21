package com.example.intervenant.myapplication;

/**
 * Created by kmoutier on 21/04/16.
 */
public class Product {
    private String name;
    private String info;
    private String image;
    private Float price;

    public Product(String name, String info, String image, Float price) {
        this.name = name;
        this.info = info;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
