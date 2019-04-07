package com.example.user_registeration.Model;

import java.util.List;


public class RequestDine {

    private String name;
  //  private String address;
    private String total;
    private List<Order> foods;
    private String timings;
    private String table;

    public RequestDine() {
    }




    public RequestDine(String name, String total, List<Order> foods, String timings, String table) {

        this.name = name;
     //   this.address = address;
        this.total = total;
        this.timings = timings;
        this.foods = foods;
        this.table = table;
    }

    public String getTable() { return table; }

    public void setTable(String table) { this.table = table; }

    public String getTimings() { return timings; }

    public void setTimings(String timings) { timings = timings; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


