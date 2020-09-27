package com.pranjal98.contagion2020;

public class DistrictClass {

    private String requestsID;
    private String device;
    private String quantity;
    private String date;
    private String status;
    private String zone;
    private String till;

    public DistrictClass(String requestsID, String device, String quantity, String date, String status, String zone, String till) {
        this.requestsID = requestsID;
        this.device = device;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.zone = zone;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
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