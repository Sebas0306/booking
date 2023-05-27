package com.qts.erp.fox.datafetchers.extra

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.dataloaders.extra.ExtraItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.models.extra.ExtraFilterInput
import com.qts.erp.fox.models.extra.ExtraModel
import com.qts.erp.fox.services.extra.ExtraService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class ExtraDataFetcher(private val extraService: ExtraService){
    @DgsQuery
    fun extraPage(@RequestHeader databaseId:String, @InputArgument filter: ExtraFilterInput?):GenericDataAccessInterface.PageResultType<ExtraModel>{
        return extraService.getPage(databaseId, filter)
    }
    @DgsData(parentType = "Booking", field = "extra")
    fun getExtraOnBooking(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<ExtraModel?>> {
        val dataLoader: DataLoader<Int?, List<ExtraModel?>> = dfe.getDataLoader(ExtraItemByIdDataLoader::class.java)
        val booking: BookingModel = dfe.getSource()
        return dataLoader.load(booking.idExtra)
    }
}