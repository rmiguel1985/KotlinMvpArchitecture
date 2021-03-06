package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room

import com.adictosalainformatica.kotlinclean.KotlinCleanApplication
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.ListAvengerDiskDataSource
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.dao.ListAvengersDao
import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.mapper.RoomMapper
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Data
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result

class ListAvengerRoomDataSourceImpl (
        private val roomDao: ListAvengersDao =
                KotlinCleanApplication.database.listAvengerDao()): ListAvengerDiskDataSource {

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
        val avengerList = RoomMapper.fromModelToRoom(avengers.data!!.results!!)

        avengerList.isNotEmpty().apply { roomDao.createAll(avengerList) }
    }

}