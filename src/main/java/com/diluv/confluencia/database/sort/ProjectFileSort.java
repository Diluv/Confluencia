package com.diluv.confluencia.database.sort;

import java.util.ArrayList;
import java.util.List;

public class ProjectFileSort {

    public static final List<Sort> LIST = new ArrayList<>();

    public static final Sort OLD = addSort(new Sort("old", "Oldest", "createdAt", Order.ASC));
    public static final Sort NEW = addSort(new Sort("new", "Newest", "createdAt", Order.DESC));
    public static final Sort DOWNLOADS = addSort(new Sort("downloads", "Downloads", "downloads", Order.DESC));
    public static final Sort SIZE = addSort(new Sort("size", "Size", "size", Order.DESC));

    private static Sort addSort (Sort sort) {

        LIST.add(sort);
        return sort;
    }
}
