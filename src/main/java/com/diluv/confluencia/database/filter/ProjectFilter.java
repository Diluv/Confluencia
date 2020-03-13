package com.diluv.confluencia.database.filter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;

public enum ProjectFilter {

    OLD("created_at", Order.ASC),
    NEW("created_at", Order.DESC),
    POPULARITY("cached_downloads", Order.DESC),
    NAME("name", Order.ASC);

    public final String filter;
    public final String order;

    ProjectFilter (String filter, Order order) {

        this.filter = filter;
        this.order = order.name;
    }

    public PreparedStatement getFilteredQuery (String query) throws SQLException {

        if (!query.contains("%filter%")) {
            System.exit(1);
        }
        String q = query.replace("'%filter%'", this.filter).replace("'%order%'", this.order);
        return Confluencia.connection().prepareStatement(q);
    }

    public static ProjectFilter fromString (String text, ProjectFilter defaultFilter) {

        for (ProjectFilter b : ProjectFilter.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return defaultFilter;
    }
}
