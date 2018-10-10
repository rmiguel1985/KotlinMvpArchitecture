package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema.RoomAvengerModel
import com.adictosalainformatica.kotlinclean.utils.Constants.DATABASE_AVENGER_TABLE_NAME

@Dao
interface ListAvengersDao {
    @Query("SELECT * FROM $DATABASE_AVENGER_TABLE_NAME")
    fun getAll(): List<RoomAvengerModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createAll (avengers: List<RoomAvengerModel>)
}