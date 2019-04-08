
package com.example.user_registeration.Model;

import java.util.List;

public class RequestHD {

    //  private String name;
    private String address;
    //private String total;
    private List<Order> foods;
    // private String ST;

    public RequestHD() {
    }


    public RequestHD(String address,List<Order> foods) {

        this.address = address;

        this.foods = foods;
    }

    public static void getStartingTime(String ST){
        ST = ST;

    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}

