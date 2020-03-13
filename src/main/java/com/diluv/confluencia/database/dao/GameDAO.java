package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.sort.GameSort;
import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.database.record.GameVersionRecord;

public interface GameDAO {

    List<GameRecord> findAll (GameSort sort);

    GameRecord findOneBySlug (String slug);

    List<GameRecord> findFeaturedGames ();

    List<GameVersionRecord> findAllGameVersionsByGameSlug (String gameSlug);
}
