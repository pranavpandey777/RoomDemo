package com.example.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    long insert(Data data);

    @Query("SELECT * FROM Data")
    List<Data> getdata();

   /* @Delete
    int delete(Data data);*/

    @Query("DELETE FROM Data WHERE id = :id")
    int delete(long id);

    /*@Update
    int update(Data data);*/

    @Query("UPDATE Data set name = :name,roll = :roll,mobile = :mobile WHERE id = :id")
    int update(long id,String name,String roll,String mobile);

    @Query("SELECT * FROM Data WHERE id = :id")
    List<Data> getonedata(long id);
}
