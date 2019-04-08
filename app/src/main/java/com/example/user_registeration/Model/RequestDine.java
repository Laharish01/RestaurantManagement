package com.example.user_registeration.Model;

import java.util.List;


public class RequestDine {

    private String TableName;
    private List<Order> foods;
    private String timings;


    public RequestDine() {
    }




    public RequestDine(String TableName,List<Order> foods, String timings) {
        this.TableName=TableName;
        this.timings = timings;
        this.foods = foods;
    }


    public String getTimings() { return timings; }

    public void setTimings(String timings) { timings = timings; }

    public String getName() {
        return TableName;
    }

    public void setName(String name) {
        this.TableName = TableName;
    }


    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}