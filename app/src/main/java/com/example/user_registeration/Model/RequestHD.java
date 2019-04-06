package com.example.user_registeration.Model;

import java.util.List;

public class RequestHD {

    private String name;
    private String address;
    private String total;
    private List<Order> foods;


    public RequestHD() {
    }


    public RequestHD(String name, String address, String total, List<Order> foods) {

        this.name = name;
        this.address = address;
        this.total = total;

        this.foods = foods;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
