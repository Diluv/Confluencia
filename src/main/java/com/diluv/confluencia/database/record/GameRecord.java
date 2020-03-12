package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRecord {
    private final String slug;
    private final String name;
    private final String url;
    private final String imageURL;
    private final String bannerURL;

    public GameRecord (ResultSet rs) throws SQLException {

        this.slug = rs.getString("slug");
        this.name = rs.getString("name");
        this.url = rs.getString("url");
        this.imageURL = rs.getString("image_url");
        this.bannerURL = rs.getString("banner_url");
    }

    public String getSlug () {

        return slug;
    }

    public String getName () {

        return this.name;
    }

    public String getUrl () {

        return this.url;
    }

    public String getImageURL () {

        return this.imageURL;
    }

    public String getBannerURL () {

        return this.bannerURL;
    }
}
