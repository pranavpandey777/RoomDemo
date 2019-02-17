package com.example.roomdemo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Data {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="roll")
    private String roll;

    @ColumnInfo(name="mobile")
    private String mobile;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public String getMobile() {
        return mobile;
    }

    public Data(String name, String roll, String mobile) {

        this.name = name;
        this.roll = roll;
        this.mobile = mobile;
    }

    public void setId(int id) {
        this.id = id;
    }
}
