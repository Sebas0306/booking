package com.qts.erp.fox.dataloaders.invoice

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.invoice.InvoiceModel
import com.qts.erp.fox.services.invoice.InvoiceService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
@DgsDataLoader
class InvoiceItemByIdDataLoader(private val invoiceService: InvoiceService): BatchLoaderWithContext<Int?, InvoiceModel?> {
    override fun load(
        ids: MutableList<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableList<InvoiceModel?>> {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync invoiceService.getListByIds(databaseId, ids)?.toMutableList()
        }
    }

}