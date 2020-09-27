package com.pranjal98.contagion2020;

public class ResourceClass {

    private String category;
    private String city;
    private String contact;
    private String descriptionandorserviceprovided;
    private String nameoftheorganisation;
    private String phonenumber;
    private String state;

    public ResourceClass(String category, String city, String contact, String descriptionandorserviceprovided, String nameoftheorganisation, String phonenumber, String state) {
        this.category = category;
        this.city = city;
        this.contact = contact;
        this.descriptionandorserviceprovided = descriptionandorserviceprovided;
        this.nameoftheorganisation = nameoftheorganisation;
        this.phonenumber = phonenumber;
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescriptionandorserviceprovided() {
        return descriptionandorserviceprovided;
    }

    public void setDescriptionandorserviceprovided(String descriptionandorserviceprovided) {
        this.descriptionandorserviceprovided = descriptionandorserviceprovided;
    }

    public String getNameoftheorganisation() {
        return nameoftheorganisation;
    }

    public void setNameoftheorganisation(String nameoftheorganisation) {
        this.nameoftheorganisation = nameoftheorganisation;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
