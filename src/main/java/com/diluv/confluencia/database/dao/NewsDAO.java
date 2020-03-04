package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.NewsRecord;
import com.diluv.confluencia.utils.Pagination;

public interface NewsDAO {

    List<NewsRecord> findAll (Pagination pagination, int limit);

    NewsRecord findOneByNewsSlug (String slug);
}
