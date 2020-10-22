package com.diluv.confluencia.utils;

import java.util.List;

public class DatabaseUtil {

    public static <R> R findOne (List<R> resultList) {

        return findOne(resultList, null);
    }

    public static <R> R findOne (List<R> resultList, R defaultReturn) {

        if (resultList.isEmpty()) {
            return defaultReturn;
        }

        return resultList.get(0);
    }
}