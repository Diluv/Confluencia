package com.diluv.confluencia.database.sort;

public class Sort {

    private final String slug;
    private final String displayName;
    private final String column;
    private final Order order;
    private final String orderName;

    public Sort (String slug, String displayName, String column, Order order) {

        this.slug = slug;
        this.displayName = displayName;
        this.column = column;
        this.order = order;
        this.orderName = order.name;
    }

    public String getQuery (String query) {

        return query.replace("'%sort%'", this.column).replace("'%order%'", this.orderName);
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

    public Order getOrder () {

        return this.order;
    }
}
