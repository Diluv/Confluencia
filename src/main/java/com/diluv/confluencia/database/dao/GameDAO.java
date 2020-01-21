package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.GameRecord;


public interface GameDAO {

    List<GameRecord> findAll ();

    GameRecord findOneBySlug (String slug);
}
