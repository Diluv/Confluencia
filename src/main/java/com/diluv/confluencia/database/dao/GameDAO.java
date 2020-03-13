package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.filter.GameFilter;
import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.database.record.GameVersionRecord;

public interface GameDAO {

    List<GameRecord> findAll (GameFilter filter);

    GameRecord findOneBySlug (String slug);

    List<GameRecord> findFeaturedGames ();

    List<GameVersionRecord> findAllGameVersionsByGameSlug (String gameSlug);
}
