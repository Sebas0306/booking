package com.qts.erp.fox.dataloaders.`package`

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.`package`.PackageModel
import com.qts.erp.fox.services.`package`.PackageService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
@DgsDataLoader
class PackageItemByIdDataLoader (private val packageService: PackageService):BatchLoaderWithContext<Int?,PackageModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        enviroment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<PackageModel?>>? {
        val databaseId : String = DgsContext.getCustomContext<AppContext>(enviroment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync packageService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }
}