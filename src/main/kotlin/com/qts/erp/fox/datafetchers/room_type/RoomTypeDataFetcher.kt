package com.qts.erp.fox.datafetchers.room_type

import com.netflix.graphql.dgs.*
import com.qts.erp.fox.dataloaders.roomtype.RoomTypeItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.room.RoomModel
import com.qts.erp.fox.models.room_type.RoomTypeFilterInput
import com.qts.erp.fox.models.room_type.RoomTypeModel
import com.qts.erp.fox.services.room_type.RoomTypeService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class RoomTypeDataFetcher(private val roomTypeService: RoomTypeService) {
    @DgsQuery
    fun roomTypePage(@RequestHeader databaseId: String, @InputArgument filter: RoomTypeFilterInput?):GenericDataAccessInterface.PageResultType<RoomTypeModel> {
        return roomTypeService.getPage(databaseId, filter)
    }
    @DgsData (parentType = "Room", field = "roomType")
    fun getRoomTypeOnRoom(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<RoomTypeModel?>>{
        val dataLoader: DataLoader<Int?, List<RoomTypeModel?>> = dfe.getDataLoader(RoomTypeItemByIdDataLoader::class.java)
        val room: RoomModel = dfe.getSource()
        return  dataLoader.load(room.idRoomType)
    }
}