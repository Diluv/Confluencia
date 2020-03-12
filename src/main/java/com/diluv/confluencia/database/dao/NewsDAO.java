package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.NewsRecord;

public interface NewsDAO {

    List<NewsRecord> findAll (long page, int limit);

    NewsRecord findOneByNewsSlug (String slug);
}
