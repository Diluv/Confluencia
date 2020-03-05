package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.database.record.GameVersionRecord;
import com.diluv.confluencia.utils.Pagination;


public interface GameDAO {

    GameRecord findOneBySlug (String slug);

    List<GameRecord> findAll (Pagination cursor, int limit);

    List<GameVersionRecord> findAllGameVersionsByGameSlug (String gameSlug, Pagination cursor, int limit);
}
