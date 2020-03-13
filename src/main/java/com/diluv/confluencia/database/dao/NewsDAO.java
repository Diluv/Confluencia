package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.sort.NewsSort;
import com.diluv.confluencia.database.record.NewsRecord;

public interface NewsDAO {

    List<NewsRecord> findAll (long page, int limit, NewsSort sort);

    NewsRecord findOneByNewsSlug (String slug);
}
