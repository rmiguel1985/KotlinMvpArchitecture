package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room

import com.adictosalainformatica.kotlinclean.KotlinCleanApplication
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.ListAvengerDiskDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.mapper.RoomMapper
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Data
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import timber.log.Timber

class ListAvengerRoomDataSourceImpl: ListAvengerDiskDataSource {
    override fun getAvengersList(): AvengersModel? {
        var avengersModel = AvengersModel()
        avengersModel.data = Data()
        val avengersList: List<Result>

        val roomDao = KotlinCleanApplication.database!!.listAvengerDao()
        avengersList = RoomMapper.fromRoomToModel(roomDao.getAll())

        avengersModel.data!!.results = avengersList

        return if (avengersModel.data!!.results!!.isNotEmpty()) {
            avengersModel
        } else {
            null
        }
    }

    override fun setAvengers(avengers: AvengersModel) {
        try {
            val roomDao = KotlinCleanApplication.database!!.listAvengerDao()

            val avengerList = RoomMapper.fromModelToRoom(avengers!!.data!!.results!!)

            roomDao.createAll(avengerList)

        } catch (exception: Exception) {
            Timber.e("Error saving avengers")
        }
    }
}