package com.example.coldstarttestapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.coldstarttestapp.data.db.ColdStart
import com.example.coldstarttestapp.data.db.MainDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){

        }

        CoroutineScope(Dispatchers.IO).launch {
            val count = getCountFromBd()
            withContext(Dispatchers.Main) {
                showToastWithCount(count)
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBoolean("hahaNotCold", true)
    }

    fun showToastWithCount(countInt: Int) {
        if (countInt == 3) {
            val toast = Toast.makeText(
                applicationContext,
                "Cold start number $countInt",
                Toast.LENGTH_SHORT
            )

            toast.show()
        }
    }

    private suspend fun getCountFromBd(isColdStart: Boolean): Int {
        val db = Room.databaseBuilder(
            applicationContext,
            MainDataBase::class.java, "database-name"
        ).build()

        val coldStartDao = it.ColdStartDao()
        val count = coldStartDao.getColdStartCount() + 1
        if(){
            coldStartDao.insertColdStart(ColdStart(count, getCurrentTime()))
        }

    }
}