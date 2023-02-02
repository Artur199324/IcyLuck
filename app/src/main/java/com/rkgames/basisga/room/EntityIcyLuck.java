package com.rkgames.basisga.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EntityIcyLuck")
public class EntityIcyLuck {

    @ColumnInfo(name = "saveEntityIcyLuck")
   public String saveEntityIcyLuck;

    @PrimaryKey(autoGenerate = true)
    int id;

    public EntityIcyLuck(String saveEntityIcyLuck) {
        this.saveEntityIcyLuck = saveEntityIcyLuck;
    }
}
