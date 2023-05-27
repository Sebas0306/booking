package com.qts.erp.fox.dataloaders.extra

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.extra.ExtraModel
import com.qts.erp.fox.services.extra.ExtraService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
@DgsDataLoader
class ExtraItemByIdDataLoader (private val extraService: ExtraService):BatchLoaderWithContext<Int?,ExtraModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        enviroment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<ExtraModel?>>? {
        val databaseId : String = DgsContext.getCustomContext<AppContext>(enviroment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync extraService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }
}