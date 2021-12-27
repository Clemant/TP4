package com.capou.tp4.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capou.tp4.model.AddressRoom

@Dao
interface AddressDao
{

    @Query("SELECT * FROM address")
    fun selectAll() : LiveData<List<AddressRoom>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chuckNorrisRoom: AddressRoom)


    @Query("DELETE FROM address")
    fun deleteAll()
}
