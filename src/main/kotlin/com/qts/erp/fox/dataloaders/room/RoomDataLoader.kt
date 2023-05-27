package com.qts.erp.fox.dataloaders.room

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.room.RoomModel
import com.qts.erp.fox.services.room.RoomService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import org.dataloader.MappedBatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader
class RoomListByHotelIdDataLoader(private val roomService: RoomService):MappedBatchLoaderWithContext<Int?, List<RoomModel?>> {
    override fun load(
        ids: MutableSet<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableMap<Int?, List<RoomModel?>>> {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync roomService.getRoomsByHotelIds(databaseId, ids).groupBy { room -> room?.idHotel }.toMutableMap()
        }
    }
}

@DgsDataLoader
class RoomItemByIdDataLoader (private val roomService: RoomService):BatchLoaderWithContext<Int?, RoomModel?>{
    override fun load(
        ids: MutableList<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<RoomModel?>> {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync roomService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }

}