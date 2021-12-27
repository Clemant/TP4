package com.capou.tp4.architecture

import android.app.Application
import androidx.room.Room

class MyApplication :Application() {


        companion object {
            lateinit var instance: MyApplication
        }


        val mApplicationDatabase: RoomData by lazy {
            Room.databaseBuilder(
                applicationContext,
                RoomData::class.java,
                "MyDatabaseName"
            ).fallbackToDestructiveMigration().build()
        }


        override fun onCreate() {
            super.onCreate()
            instance = this
        }
}