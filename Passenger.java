package com.company;

public class Passenger {
    private int guestNum;
    private String firstName;
    private String surName;
    private int expenses;


    public Passenger(int gNum, String fName, String sName,int exp){
        this.guestNum = gNum;
        this.firstName = fName;
        this.surName = sName;
        this.expenses = exp;
    }

    // set method
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public void setSurName(String sName) {
        this.surName = sName;
    }

    public void setGuestNum(int gNum) {
        this.guestNum = gNum;
    }

    public void setExpenses(int exp) {
        this.expenses = exp;
    }

    // Get method

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurName() {
        return this.surName;
    }

    public int getGuestNum() {
        return this.guestNum;
    }

    public int getExpenses() {
        return this.expenses;
    }
}
