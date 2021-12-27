package com.capou.tp4.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capou.tp4.dao.AddressDao
import com.capou.tp4.model.AddressRoom


@Database(
    entities = [
        AddressRoom::class
    ],
    version = 2,
    exportSchema = false
)
abstract class  RoomData : RoomDatabase() {

    abstract fun addressDao() : AddressDao
}