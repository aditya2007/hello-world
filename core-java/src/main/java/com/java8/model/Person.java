package com.java8.model;

/**
 * Created by Yogananda Gowda - 212590467 on 6/26/17.
 */
public class Person {
    private Integer id;
    private String name;
    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
