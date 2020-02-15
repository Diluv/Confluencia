package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.NewsRecord;

public interface NewsDAO {

    List<NewsRecord> findAll ();

    NewsRecord findOneByNewsSlug (String slug);
}
