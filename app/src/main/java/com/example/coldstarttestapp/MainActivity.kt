package com.example.coldstarttestapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.coldstarttestapp.data.app.MyApp
import com.example.coldstarttestapp.data.db.MainDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val count = getCountFromBd()
            withContext(Dispatchers.Main){
                showToastWithCount(count)
            }
        }
    }

    fun showToastWithCount(countInt: Int?) {
        if(countInt == 3){
            val toast = Toast.makeText(
                applicationContext,
                "Cold start number $countInt",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }else if(countInt == null ){
            val toast = Toast.makeText(
                applicationContext,
                "Error with local bd",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun getCountFromBd(): Int? {
        MyApp.db?.let {
            val coldStartDao = it.ColdStartDao()
            return coldStartDao.getColdStartCount()
        }
        return null
    }
}