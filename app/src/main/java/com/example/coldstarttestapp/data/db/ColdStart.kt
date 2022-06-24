package com.example.coldstarttestapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ColdStart(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "time") val time: String?
)