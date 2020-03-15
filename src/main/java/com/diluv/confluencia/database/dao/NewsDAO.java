package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.NewsRecord;
import com.diluv.confluencia.database.sort.NewsSort;

public interface NewsDAO {

    List<NewsRecord> findAll (long page, int limit, NewsSort sort);

    NewsRecord findOneByNewsSlug (String slug);
}
