package com.diluv.confluencia.database.filter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;

public enum GameFilter {

    NAME("name", Order.ASC),
    OLD("created_at", Order.ASC),
    NEW("created_at", Order.DESC);

    public final String filter;
    public final String order;

    GameFilter (String filter, Order order) {

        this.filter = filter;
        this.order = order.name;
    }

    public PreparedStatement getFilteredQuery (String query) throws SQLException {

        if (!query.contains("%filter%")) {
            System.exit(1);
        }
        return Confluencia.connection().prepareStatement(query.replace("'%filter%'", this.filter).replace("'%order%'", this.order));
    }

    public static GameFilter fromString (String text, GameFilter defaultFilter) {

        for (GameFilter b : GameFilter.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return defaultFilter;
    }
}
