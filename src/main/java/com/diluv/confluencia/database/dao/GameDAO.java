package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.database.record.GameVersionRecord;

public interface GameDAO {

    List<GameRecord> findAll ();

    GameRecord findOneBySlug (String slug);

    List<GameVersionRecord> findAllGameVersionsByGameSlug (String gameSlug);
}
