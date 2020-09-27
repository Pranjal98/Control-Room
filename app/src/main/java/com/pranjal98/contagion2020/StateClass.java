package com.pranjal98.contagion2020;

public class StateClass{

    private String requestsID;
    private String device;
    private String quantity;
    private String date;
    private String status;
    private String till;

    public StateClass(String requestsID, String device, String quantity, String date, String status, String till) {
        this.requestsID = requestsID;
        this.device = device;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.till = till;
    }

    public String getRequestsID() {
        return requestsID;
    }

    public String getDevice() {
        return device;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

    public void setRequestsID(String requestsID) {
        this.requestsID = requestsID;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
