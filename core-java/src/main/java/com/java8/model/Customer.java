package com.java8.model;

/**
 * Created by 212590467 on 11/27/16.
 */
public enum Customer {
    ORACLE("Oracle"),
    MICROSOFT("Microsoft"),
    GOOGLE("Google"),
    GE("General Electric");

    private String name;

    private Customer(String name) {
        this.name =  name;
    }

    public String getCustomer() {
        return name;
    }
}
