package com.diluv.confluencia.database.sort;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;

public enum ProjectSort {

    OLD("created_at", Order.ASC),
    NEW("created_at", Order.DESC),
    POPULARITY("cached_downloads", Order.DESC),
    NAME("name", Order.ASC);

    public final String sort;
    public final String order;

    ProjectSort (String sort, Order order) {

        this.sort = sort;
        this.order = order.name;
    }

    public PreparedStatement getQuery (String query) throws SQLException {

        return Confluencia.connection().prepareStatement(query.replace("'%sort%'", this.sort).replace("'%order%'", this.order));
    }

    public static ProjectSort fromString (String text, ProjectSort defaultSort) {

        for (ProjectSort b : ProjectSort.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return defaultSort;
    }
}
