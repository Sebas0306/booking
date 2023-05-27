package com.qts.erp.fox.datafetchers.`package`

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.dataloaders.`package`.PackageItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.models.`package`.PackageFilterInput
import com.qts.erp.fox.models.`package`.PackageModel
import com.qts.erp.fox.services.`package`.PackageService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class PackageDataFetcher(private val packageService: PackageService) {
    @DgsQuery
    fun packagePage(@RequestHeader databaseId:String, @InputArgument filter: PackageFilterInput?):GenericDataAccessInterface.PageResultType<PackageModel>{
        return packageService.getPage(databaseId, filter)
    }
    @DgsData(parentType = "Booking", field = "package")
    fun getPackageOnBooking(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<PackageModel?>> {
        val dataLoader: DataLoader<Int?, List<PackageModel?>> = dfe.getDataLoader(PackageItemByIdDataLoader::class.java)
        val booking: BookingModel = dfe.getSource()
        return dataLoader.load(booking.idPackage)
    }
}