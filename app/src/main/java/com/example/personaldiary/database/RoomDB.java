package com.example.personaldiary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.personaldiary.model.Day;
import java.security.KeyStore;


@Database(entities = Day.class,version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "Journal";
    public synchronized static RoomDB getInstance(Context context){
        if(database==null)
        {
            database= Room.databaseBuilder(context.getApplicationContext(),
                RoomDB.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        }
        return database;

    }
    public abstract MainDAO mainDAO();
}