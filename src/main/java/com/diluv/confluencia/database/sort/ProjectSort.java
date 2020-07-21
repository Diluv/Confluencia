package com.diluv.confluencia.database.sort;

import java.util.ArrayList;
import java.util.List;

public class ProjectSort {

    public static final List<Sort> LIST = new ArrayList<>();

    public static final Sort OLD = addSort(new Sort("old", "Oldest", "createdAt", Order.ASC));
    public static final Sort NEW = addSort(new Sort("new", "Newest", "createdAt", Order.DESC));
    public static final Sort POPULAR = addSort(new Sort("popular", "Popular", "cachedDownloads", Order.DESC));
    public static final Sort NAME = addSort(new Sort("name", "Name", "name", Order.ASC));

    private static Sort addSort (Sort sort) {

        LIST.add(sort);
        return sort;
    }
}
