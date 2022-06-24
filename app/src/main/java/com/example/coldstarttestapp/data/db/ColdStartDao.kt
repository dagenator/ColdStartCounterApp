package com.example.coldstarttestapp.data.db

import androidx.room.*

@Dao
interface ColdStartDao {
    @Query("SELECT * FROM ColdStart")
    fun getAll(): List<ColdStart>

    @Query("SELECT count(*) FROM ColdStart")
    fun getColdStartCount(): Int

    @Insert
    fun insertColdStart(vararg coldStart: ColdStart)

    @Query("DELETE FROM ColdStart")
    fun deleteAllInTable()
}