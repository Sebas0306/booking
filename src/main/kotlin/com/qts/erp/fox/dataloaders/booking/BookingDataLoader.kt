package com.qts.erp.fox.dataloaders.booking

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.services.booking.BookingService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
@DgsDataLoader
class BookingItemByIdDataLoader (private val bookingService: BookingService): BatchLoaderWithContext<Int?, BookingModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<BookingModel?>>? {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync bookingService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }
}