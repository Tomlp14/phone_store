package com.tommywebdesigns.phonestore.model.data;

public class Phone{

    private int phoneId;
    private PhoneBrands phoneBrand;
    private String model;
    private double price;

    public Phone(PhoneBrands phoneBrand, String model, double price) {
        this.phoneBrand = phoneBrand;
        this.model = model;
        this.price = price;
    }
    public Phone(int phoneId, PhoneBrands phoneBrand, String model, double price) {
        this.phoneId = phoneId;
        this.phoneBrand = phoneBrand;
        this.model = model;
        this.price = price;
    }
    public Phone(){ }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public PhoneBrands getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(PhoneBrands phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
