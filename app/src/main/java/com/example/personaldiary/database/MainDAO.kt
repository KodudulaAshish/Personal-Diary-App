package com.example.personaldiary.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.personaldiary.model.Day
import java.io.Serializable


@Dao
interface MainDAO {
    @Insert(onConflict = REPLACE)
    fun insert(day: Day)

    @Query("SELECT * FROM Entry")
    fun getAll(): List<Day>

    @Query("UPDATE Entry SET day=:day, content=:content WHERE ID=:ID")
    fun update(ID: Int, day: String, content: String)

    @Delete
    fun delete(day: Day)
    fun insert(modified_day: Serializable) {

    }
}