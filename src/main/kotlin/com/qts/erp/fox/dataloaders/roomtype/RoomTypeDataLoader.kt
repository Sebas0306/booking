package com.qts.erp.fox.dataloaders.roomtype

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.room_type.RoomTypeModel
import com.qts.erp.fox.services.room_type.RoomTypeService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader
class RoomTypeItemByIdDataLoader(private val roomTypeService: RoomTypeService):BatchLoaderWithContext<Int?, RoomTypeModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        enviroment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<RoomTypeModel?>> {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(enviroment!!).databaseId!!
        return CompletableFuture.supplyAsync {
           return@supplyAsync roomTypeService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }

}