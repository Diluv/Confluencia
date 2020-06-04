package com.diluv.confluencia.database.sort;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;

public class Sort {

    private final String displayName;
    private final String sort;
    private final String order;

    public Sort (String displayName, String sort, Order order) {

        this.displayName = displayName;
        this.sort = sort;
        this.order = order.name;
    }

    public PreparedStatement getQuery (String query) throws SQLException {

        final String replace = query.replace("'%sort%'", this.sort).replace("'%order%'", this.order);
        return Confluencia.connection().prepareStatement(replace);
    }

    public String getDisplayName () {

        return this.displayName;
    }

    public String getSort () {

        return this.sort;
    }

    public String getOrder () {

        return this.order;
    }
}
