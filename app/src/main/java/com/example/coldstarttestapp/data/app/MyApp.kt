package com.example.coldstarttestapp.data.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.coldstarttestapp.data.db.ColdStart
import com.example.coldstarttestapp.data.db.MainDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            setDB(applicationContext)
            db?.let {
                val coldStartDao = it.ColdStartDao()
                val count = coldStartDao.getColdStartCount() + 1
                coldStartDao.insertColdStart(ColdStart(count, getCurrentTime()))
            }
        }
    }

    private fun getCurrentTime(): String {
        return DateFormat.getDateInstance().format(Date())
    }

    companion object {
        var db: MainDataBase? = null
        fun setDB(applicationContext: Context) {
            db = Room.databaseBuilder(
                applicationContext,
                MainDataBase::class.java, "database-name"
            ).build()
        }
    }
}