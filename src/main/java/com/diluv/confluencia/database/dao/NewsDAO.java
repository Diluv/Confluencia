package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.filter.NewsFilter;
import com.diluv.confluencia.database.record.NewsRecord;

public interface NewsDAO {

    List<NewsRecord> findAll (long page, int limit, NewsFilter filter);

    NewsRecord findOneByNewsSlug (String slug);
}
