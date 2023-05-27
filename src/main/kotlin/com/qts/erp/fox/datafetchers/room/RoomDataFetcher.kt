package com.qts.erp.fox.datafetchers.room

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.dataloaders.room.RoomItemByIdDataLoader
import com.qts.erp.fox.dataloaders.room.RoomListByHotelIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.models.hotel.HotelModel
import com.qts.erp.fox.models.room.RoomFilterInput
import com.qts.erp.fox.models.room.RoomModel
import com.qts.erp.fox.services.room.RoomService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class RoomDataFetcher (private val roomService: RoomService){
    @DgsQuery
    fun roomPage(@RequestHeader databaseId: String, @InputArgument filter: RoomFilterInput?):GenericDataAccessInterface.PageResultType<RoomModel>{
        return roomService.getPage(databaseId, filter)
    }
    @DgsData(parentType = "Hotel", field = "rooms")
    fun getRoomsOnHotel(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<RoomModel?>> {
        val dataLoader: DataLoader<Int?, List<RoomModel?>> = dfe.getDataLoader(RoomListByHotelIdDataLoader::class.java)
        val hotel: HotelModel = dfe.getSource()
        return dataLoader.load(hotel.id)
    }
    @DgsData(parentType = "Booking", field = "room")
    fun getRoomOnBooking(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String):CompletableFuture<List<RoomModel?>>{
        val dataLoader: DataLoader<Int?, List<RoomModel?>> = dfe.getDataLoader(RoomItemByIdDataLoader::class.java)
        val booking: BookingModel = dfe.getSource()
        return dataLoader.load(booking.idRoom)
    }
}