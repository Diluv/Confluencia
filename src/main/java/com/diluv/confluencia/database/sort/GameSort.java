package com.diluv.confluencia.database.sort;

import java.util.ArrayList;
import java.util.List;

public class GameSort {

    public static final List<Sort> LIST = new ArrayList<>();

    public static final Sort NAME = addSort(new Sort("name", "Name", "name", Order.ASC));
    public static final Sort OLD = addSort(new Sort("old", "Oldest", "created_at", Order.ASC));
    public static final Sort NEW = addSort(new Sort("new", "Newest", "created_at", Order.DESC));

    private static Sort addSort (Sort sort) {

        LIST.add(sort);
        return sort;
    }
}
