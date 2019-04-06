package com.example.user_registeration.Model;

import java.util.List;


public class RequestDine {

    private String name;
    private String address;
    private String total;
    private List<Order> foods;
    private String Timings;

    public RequestDine() {
    }


    public RequestDine(String name, String address, String total, List<Order> foods, String timings) {

        this.name = name;
        this.address = address;
        this.total = total;
        this.Timings = timings;
        this.foods = foods;
    }

    public String getTimings() { return Timings; }

    public void setTimings(String timings) { Timings = timings; }

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


