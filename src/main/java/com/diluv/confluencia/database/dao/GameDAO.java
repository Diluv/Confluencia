package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.utils.Pagination;


public interface GameDAO {

    List<GameRecord> findAll (Pagination cursor, int limit);

    GameRecord findOneBySlug (String slug);
}
