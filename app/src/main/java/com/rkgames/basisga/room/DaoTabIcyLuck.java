package com.rkgames.basisga.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface DaoTabIcyLuck {

    @Insert
    void insertIcyLuck(EntityIcyLuck entityIcyLuck);

    @Insert
    void insertIcyLuckData(EntityIcyLuckData entityIcyLuckData);

    @Query("SELECT * FROM EntityIcyLuckData ORDER BY ID DESC LIMIT 1")
    Flowable<List<EntityIcyLuckData>> getEntityIcyLuckData();

    @Query("SELECT * FROM EntityIcyLuck ORDER BY ID DESC LIMIT 1")
    Single<EntityIcyLuck> getEntityIcyLuck();
}
