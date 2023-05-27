package com.qts.erp.fox.dataloaders.client

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.client.ClientModel
import com.qts.erp.fox.services.client.ClientService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage


@DgsDataLoader
class ClientItemByIdDataLoader (private val clientService: ClientService):BatchLoaderWithContext<Int?, ClientModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        enviroment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<ClientModel?>> {
        val databaseId : String = DgsContext.getCustomContext<AppContext>(enviroment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync clientService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }

}