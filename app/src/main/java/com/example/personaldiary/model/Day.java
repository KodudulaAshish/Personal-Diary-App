package com.example.personaldiary.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity(tableName = "Entry")
public class Day implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int ID=0;


    @ColumnInfo(name="day")
    String day="";


    @ColumnInfo(name = "Content")
    String content="";


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

