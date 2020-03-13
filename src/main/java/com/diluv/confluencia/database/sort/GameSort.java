package com.diluv.confluencia.database.sort;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;

public enum GameSort {

    NAME("name", Order.ASC),
    OLD("created_at", Order.ASC),
    NEW("created_at", Order.DESC);

    public final String sort;
    public final String order;

    GameSort (String sort, Order order) {

        this.sort = sort;
        this.order = order.name;
    }

    public PreparedStatement getQuery (String query) throws SQLException {

        return Confluencia.connection().prepareStatement(query.replace("'%sort%'", this.sort).replace("'%order%'", this.order));
    }

    public static GameSort fromString (String text, GameSort defaultSort) {

        for (GameSort b : GameSort.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return defaultSort;
    }
}
