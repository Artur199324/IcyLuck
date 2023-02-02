package com.rkgames.basisga.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EntityIcyLuck.class,EntityIcyLuckData.class},version = 1,exportSchema = false)
abstract public class DatabaseinstanceIcyLuck extends RoomDatabase {
    public abstract DaoTabIcyLuck daoTabIcyLuck();

    private static volatile DatabaseinstanceIcyLuck INSTANCE;

    public static DatabaseinstanceIcyLuck getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseinstanceIcyLuck.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseinstanceIcyLuck.class, "icyLuck43")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
