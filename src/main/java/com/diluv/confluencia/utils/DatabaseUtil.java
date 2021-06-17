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

        R result = resultList.get(0);
        if (result == null) {
            return defaultReturn;
        }
        return result;
    }
}