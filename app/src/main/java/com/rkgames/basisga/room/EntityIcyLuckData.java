package com.rkgames.basisga.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EntityIcyLuckData")
public class EntityIcyLuckData {
    @ColumnInfo(name = "go")
    public String go;
    @PrimaryKey(autoGenerate = true)
    int id;

    public EntityIcyLuckData(String go) {
        this.go = go;
    }
}
