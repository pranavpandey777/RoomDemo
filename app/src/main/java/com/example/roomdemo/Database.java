package com.example.roomdemo;

import android.arch.persistence.room.RoomDatabase;

@android.arch.persistence.room.Database(entities = {Data.class}, version = 1)
public abstract class Database extends RoomDatabase {
    abstract  DataDao dataDao();

}
