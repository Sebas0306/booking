package com.qts.erp.fox.datafetchers.hotel

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.qts.erp.fox.dataloaders.hotel.HotelItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.hotel.HotelModel
import com.qts.erp.fox.models.room.RoomModel
import com.qts.erp.fox.services.hotel.HotelService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class HotelDataFetcher(private val hotelService : HotelService){
    @DgsQuery
    fun hotelPage(@RequestHeader databaseId: String): GenericDataAccessInterface.PageResultType<HotelModel>{
        return  hotelService.getPage(databaseId, null)
    }
    @DgsData(parentType = "Room", field = "hotel")
    fun getHotelOnRoom(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<HotelModel?>> {
        val dataLoader: DataLoader<Int?, List<HotelModel?>> = dfe.getDataLoader(HotelItemByIdDataLoader::class.java)
        val room: RoomModel = dfe.getSource()
        return dataLoader.load(room.idHotel)
    }
}

