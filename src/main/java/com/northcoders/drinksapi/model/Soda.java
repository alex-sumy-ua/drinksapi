package com.northcoders.drinksapi.model;

public class Soda {

    private long id;
    String name;

    public Soda(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
