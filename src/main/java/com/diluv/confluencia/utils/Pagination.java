package com.diluv.confluencia.utils;


import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Pagination {

    private static Gson gson = new GsonBuilder().create();

    @Expose
    public long offset;

    public Pagination (long offset) {

        this.offset = offset;
    }

    public static int getLimit (Integer queryLimit) {

        if (queryLimit == null) {
            return 20;
        }

        if (queryLimit <= 20) {
            return 20;
        }

        if (queryLimit >= 100) {
            return 100;
        }

        return queryLimit;
    }

    public String getCursor () {

        return Base64.getEncoder().encodeToString(gson.toJson(this).getBytes());
    }

    public static Pagination getPagination (String cursor) {

        if (cursor == null) {
            return new Pagination(0);
        }
        return gson.fromJson(new String(Base64.getDecoder().decode(cursor)), Pagination.class);
    }
}
