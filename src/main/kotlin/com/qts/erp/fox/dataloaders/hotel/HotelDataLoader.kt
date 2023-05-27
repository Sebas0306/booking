package com.qts.erp.fox.dataloaders.hotel

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.hotel.HotelModel
import com.qts.erp.fox.services.hotel.HotelService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader
class HotelItemByIdDataLoader(private val hotelService: HotelService): BatchLoaderWithContext<Int?, HotelModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<HotelModel?>> {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync hotelService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }
}