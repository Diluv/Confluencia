package com.diluv.confluencia.database.sort;

public enum Order {

    ASC("asc"),
    DESC("desc");

    public final String name;

    Order (String name) {

        this.name = name;
    }
}
