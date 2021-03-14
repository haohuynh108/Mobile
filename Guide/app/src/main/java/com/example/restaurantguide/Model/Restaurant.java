package com.example.restaurantguide.Model;

public class Restaurant {
    public int id;
    public String rName, rAddress,rPhone, rTag, rDesc, rRating;

    public Restaurant(int id, String rName, String rAddress, String rPhone, String rTag, String rDesc, String rRating) {
        this.id = id;
        this.rName = rName;
        this.rAddress = rAddress;
        this.rPhone = rPhone;
        this.rTag = rTag;
        this.rDesc = rDesc;
        this.rRating = rRating;
    }

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone;
    }

    public String getrTag() {
        return rTag;
    }

    public void setrTag(String rTag) {
        this.rTag = rTag;
    }

    public String getrDesc() {
        return rDesc;
    }

    public void setrDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    public String getrRating() {
        return rRating;
    }

    public void setrRating(String rRating) {
        this.rRating = rRating;
    }
}
