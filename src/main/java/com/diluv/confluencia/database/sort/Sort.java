package com.diluv.confluencia.database.sort;

public class Sort {

    private final String slug;
    private final String displayName;
    private final String column;
    private final Order order;

    public Sort (String slug, String displayName, String column, Order order) {

        this.slug = slug;
        this.displayName = displayName;
        this.column = column;
        this.order = order;
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

    public String getSQL () {

        return String.format("ORDER BY %s %s", this.getColumn(), this.getOrder().name);
    }
}
