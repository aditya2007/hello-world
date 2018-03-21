package com.java8.model;

/**
 * Created by Yogananda Gowda - 212590467 on 6/26/17.
 */
public class Transaction {

    public enum Type {
        GROCERY,
        MERCHANDISE,
        GIFTS
    }

    private Integer id;
    private Type type;
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
