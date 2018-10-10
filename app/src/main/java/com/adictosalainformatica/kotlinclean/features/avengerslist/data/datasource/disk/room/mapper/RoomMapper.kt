package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.mapper

import com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.disk.room.schema.RoomAvengerModel
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Result
import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.Thumbnail
import java.util.ArrayList

/**
 * RealmMapper Class
 *
 *
 * Class to map from room model to avenger model and vice versa
 */
object RoomMapper {

    /**
     * Convert realm model to avenger model
     *
     * @param roomAvengerModel
     * @return Result avenger list
     */
    fun fromRoomToModel(roomAvengerModel: List<RoomAvengerModel>): List<Result> {

        val avengersList = ArrayList<Result>()

        for (avengerRoom in roomAvengerModel) {
            val avenger = Result()
            val thumbnail = Thumbnail()

            avenger.id = avengerRoom.id
            avenger.name =avengerRoom.avengerName
            avenger.description =avengerRoom.description
            avenger.modified =avengerRoom.avengerDateUpdate
            thumbnail.path =avengerRoom.imageUrlPath
            thumbnail.extension = avengerRoom.imageUrlExtension
            avenger.thumbnail =thumbnail
            avengersList.add(avenger)
        }

        return avengersList
    }

    /**
     * Convert avenger model to realm model list
     *
     * @param results
     * @return RealmAvengerModel list
     */
    fun fromModelToRoom(results: List<Result>): List<RoomAvengerModel> {

        val roomAvengerModels = ArrayList<RoomAvengerModel>()

        for (avenger in results) {
            val roomAvengerModel = RoomAvengerModel()

            roomAvengerModel.id = avenger.id!!
            roomAvengerModel.avengerName = avenger.name
            roomAvengerModel.description = avenger.description
            roomAvengerModel.avengerDateUpdate = avenger.modified
            roomAvengerModel.imageUrlPath = avenger.thumbnail!!.path
            roomAvengerModel.imageUrlExtension = avenger.thumbnail!!.extension
            avenger.thumbnail =avenger.thumbnail

            roomAvengerModels.add(roomAvengerModel)
        }
        return roomAvengerModels
    }
}