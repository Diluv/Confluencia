package com.diluv.confluencia.database.sort;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;

public enum NewsSort {

    OLD("created_at", Order.ASC),
    NEW("created_at", Order.DESC);

    public final String sort;
    public final String order;

    NewsSort (String sort, Order order) {

        this.sort = sort;
        this.order = order.name;
    }

    public PreparedStatement getQuery (String query) throws SQLException {
        return Confluencia.connection().prepareStatement(query.replace("'%sort%'", this.sort).replace("'%order%'", this.order));
    }

    public static NewsSort fromString (String text, NewsSort defaultSort) {

        for (NewsSort b : NewsSort.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return defaultSort;
    }
}
