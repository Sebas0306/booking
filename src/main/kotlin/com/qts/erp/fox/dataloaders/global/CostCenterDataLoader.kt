package com.qts.erp.fox.dataloaders.global

import com.netflix.graphql.dgs.DgsDataLoader
import com.netflix.graphql.dgs.context.DgsContext
import com.qts.erp.fox.config.AppContext
import com.qts.erp.fox.models.global.CostCenterModel
import com.qts.erp.fox.services.global.CostCenterService
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.BatchLoaderWithContext
import org.dataloader.MappedBatchLoaderWithContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader
class CostCenterListBySellerIds(private val costCenterService: CostCenterService): MappedBatchLoaderWithContext<Int?, List<CostCenterModel?>> {
    override fun load(
        keys: MutableSet<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableMap<Int?, List<CostCenterModel?>>> {
        val databaseId = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
           return@supplyAsync costCenterService.getListBySellerIds(databaseId, keys).groupBy { costCenter -> costCenter?.sellerId }.toMutableMap()
       }
    }
}
@DgsDataLoader
class CostCenterListByUserName(private val costCenterService: CostCenterService): MappedBatchLoaderWithContext<String?, List<CostCenterModel?>> {
    override fun load(
        usernames: MutableSet<String?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableMap<String?, List<CostCenterModel?>>> {
        val databaseId: String = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync costCenterService.getListByUserName(databaseId, usernames).groupBy { costCenter -> costCenter?.username }.toMutableMap()
        }
    }
}

@DgsDataLoader
class CostCenterItemByIds(private val costCenterService: CostCenterService): BatchLoaderWithContext<Int?, CostCenterModel?> {
    override fun load(ids: MutableList<Int?>?, environment: BatchLoaderEnvironment?): CompletionStage<MutableList<CostCenterModel?>> {
        val databaseId = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync costCenterService.getListByIds(databaseId, ids).toMutableList()
        }
    }
}

@DgsDataLoader
class CostCenterListForOrdersBySellerIds(private val costCenterService: CostCenterService): MappedBatchLoaderWithContext<Int?, List<CostCenterModel?>> {
    override fun load(
        keys: MutableSet<Int?>?,
        environment: BatchLoaderEnvironment?
    ): CompletionStage<MutableMap<Int?, List<CostCenterModel?>>> {
        val databaseId = DgsContext.getCustomContext<AppContext>(environment!!).databaseId!!
        return CompletableFuture.supplyAsync {
            return@supplyAsync costCenterService.getOrdersCostCentersListBySellerIds(databaseId, keys).groupBy { costCenter -> costCenter?.sellerId }.toMutableMap()
        }
    }
}
