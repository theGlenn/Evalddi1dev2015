package com.example.intervenant.myapplication;

/**
 * Created by ksomville on 21/04/2016.
 */
public class ProductObject {
    public String name;
    public String image;
    public int resImage;
    public String info;
    public String price;


    public ProductObject(int resImage, String name){
        this.name = name;
        this.resImage = resImage;
    }

    public ProductObject(String name, String info, String image, String price) {
        this.name =  name;
        this.info = info;
        this.image = image;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ProductObject){
            return ((ProductObject)o).name.equals(this.name);
        }
        return false;
    }
}
