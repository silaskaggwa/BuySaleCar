package model;

import java.util.Date;

public class Car {

    private int id;
    private String model;
    private String brand;
    private double price;
    private String color;
    private boolean isSold;
    private Date date;
    private double discount;
    private String image;

    public Car(int id, String model, String brand, double price, String color, boolean isSold, Date date, double discount, String image) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.isSold = isSold;
        this.date = date;
        this.discount = discount;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
