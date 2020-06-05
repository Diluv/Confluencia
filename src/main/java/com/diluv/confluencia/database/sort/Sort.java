package com.diluv.confluencia.database.sort;

import com.diluv.confluencia.Confluencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sort {

    private final String slug;
    private final String displayName;
    private final String column;
    private final String order;

    public Sort (String slug, String displayName, String column, Order order) {

        this.slug = slug;
        this.displayName = displayName;
        this.column = column;
        this.order = order.name;
    }

    public PreparedStatement getQuery (String query) throws SQLException {

        final String replace = query.replace("'%sort%'", this.column).replace("'%order%'", this.order);
        return Confluencia.connection().prepareStatement(replace);
    }

    public String getSlug () {

        return this.slug;
    }

    public String getDisplayName () {

        return this.displayName;
    }

    public String getColumn () {

        return this.column;
    }

    public String getOrder () {

        return this.order;
    }
}
