package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.NewsRecord;
import com.diluv.confluencia.database.sort.NewsSort;
import com.diluv.confluencia.utils.SQLHandler;

public class NewsDatabase {
    private static final String FIND_ALL = SQLHandler.readFile("news/findAll");
    private static final String FIND_ONE_BY_SLUG = SQLHandler.readFile("news/findAllBySlug");

    public List<NewsRecord> findAll (long page, int limit, NewsSort sort) {

        List<NewsRecord> news = new ArrayList<>();

        try (PreparedStatement stmt = sort.getQuery(FIND_ALL)) {
            stmt.setLong(1, (page - 1) * limit);
            stmt.setLong(2, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    news.add(new NewsRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public NewsRecord findOneByNewsSlug (String slug) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_BY_SLUG)) {
            stmt.setString(1, slug);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new NewsRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to project file queue by id.", e);
        }
        return null;
    }
}
