package com.qts.erp.fox.datafetchers.accounts

//import com.google.gson.Gson
//import com.netflix.graphql.dgs.DgsComponent
//import com.netflix.graphql.dgs.DgsData
//import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
//import com.qts.erp.fox.dataloaders.accounts.DatabaseListByServerIdsDataLoader
//import com.qts.erp.fox.models.accounts.DBModel
//import com.qts.erp.fox.models.accounts.ServerModel
//import org.dataloader.DataLoader
//import org.slf4j.LoggerFactory
//import java.util.concurrent.CompletableFuture
//
//@DgsComponent
//class DBDataFetcher {
//    companion object {
//        private val logger = LoggerFactory.getLogger(DBDataFetcher::class.java)
//        private val gson = Gson()
//    }
//
//    @DgsData(parentType = "Server", field = "databases")
//    fun getDatabaseListByServerIds(dfe: DgsDataFetchingEnvironment): CompletableFuture<List<DBModel?>> {
//        val dataloader : DataLoader<Int, List<DBModel?>> = dfe.getDataLoader(DatabaseListByServerIdsDataLoader::class.java)
//        val server: ServerModel = dfe.getSource()
//        return dataloader.load(server.id)
//    }
//}