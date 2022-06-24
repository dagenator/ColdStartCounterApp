package com.example.coldstarttestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ColdStart::class], version = 1)
abstract class MainDataBase : RoomDatabase() {
    abstract fun ColdStartDao(): ColdStartDao
}