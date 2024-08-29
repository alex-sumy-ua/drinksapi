package com.northcoders.drinksapi.model;

public class Tea {

    private long id;
    String name;

    public Tea(long id, String name) {
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
