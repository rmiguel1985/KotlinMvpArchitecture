package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.dao.ListAvengersDao
import com.adictosalainformatica.kotlinclean.utils.Constants.DATABASE_VERSION

@Database(entities = [RoomAvengerModel::class],
        version = DATABASE_VERSION,
        exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listAvengerDao(): ListAvengersDao
}