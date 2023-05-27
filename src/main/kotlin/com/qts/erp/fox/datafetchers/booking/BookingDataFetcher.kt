package com.qts.erp.fox.datafetchers.booking

import com.netflix.graphql.dgs.*
import com.qts.erp.fox.dataloaders.booking.BookingItemByIdDataLoader
import com.qts.erp.fox.dataloaders.client.ClientItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.booking.BookingFilterInput
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.models.client.ClientModel
import com.qts.erp.fox.models.invoice_details.InvoiceDetailsModel
import com.qts.erp.fox.services.booking.BookingService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class BookingDataFetcher(private val bookingService: BookingService){
    @DgsQuery
    fun bookingPage(@RequestHeader databaseId: String, @InputArgument filter: BookingFilterInput?): GenericDataAccessInterface.PageResultType<BookingModel>{
        return bookingService.getPage(databaseId, filter)
    }
    @DgsData(parentType = "InvoiceDetails", field = "booking")
    fun getBookingOnInvoiceDetail(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<BookingModel?>> {
        val dataLoader: DataLoader<Int?, List<BookingModel?>> = dfe.getDataLoader(BookingItemByIdDataLoader::class.java)
        val invoiceDetail: InvoiceDetailsModel = dfe.getSource()
        return dataLoader.load(invoiceDetail.idBooking)
    }


}