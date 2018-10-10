package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "avengers")
data class RoomAvengerModel (
    @PrimaryKey
    var id: Int = 0,
    var avengerName: String? = null,
    var avengerDateUpdate: String? = null,
    var imageUrlPath: String? = null,
    var imageUrlExtension: String? = null,
    var description: String? = null
)