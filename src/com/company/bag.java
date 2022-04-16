package com.company;

import java.util.ArrayList;

public class bag {
    private final String color;
    private final ArrayList<String> containedBagsList = new ArrayList<>();
    private boolean containingShinyGold = false;

    public bag(String color){   //constructor
        this.color = color;
    }


    //setter

    public void addContainedBag(String addedBag){
        this.containedBagsList.add(addedBag);
    }

    public void setContainingShinyGold(boolean bl) {
        this.containingShinyGold = bl;
    }

    //getter
    public String getColor() {
        return this.color;
    }

    public boolean isContainingShinyGold(){
        return containingShinyGold;
    }

    public ArrayList<String> getContainedBagsList() {
        return containedBagsList;
    }
}
