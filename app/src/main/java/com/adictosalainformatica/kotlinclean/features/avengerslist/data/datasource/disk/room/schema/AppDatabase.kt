package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.dao.ListAvengersDao

@Database(entities = [RoomAvengerModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listAvengerDao(): ListAvengersDao
}