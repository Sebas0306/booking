package com.qts.erp.fox.datafetchers.invoice_details

import com.netflix.graphql.dgs.*
import com.qts.erp.fox.dataloaders.invoice.InvoiceItemByIdDataLoader
import com.qts.erp.fox.dataloaders.room.RoomItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.models.invoice.InvoiceFilterInput
import com.qts.erp.fox.models.invoice.InvoiceModel
import com.qts.erp.fox.models.invoice_details.InvoiceDetailsModel
import com.qts.erp.fox.models.room.RoomModel
import com.qts.erp.fox.services.invoice_details.InvoiceDetailsService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class InvoiceDetailsDataFetcher(private val invoiceDetailsService: InvoiceDetailsService){
    @DgsQuery
    fun invoiceDetailPage(@RequestHeader databaseId: String):GenericDataAccessInterface.PageResultType<InvoiceDetailsModel>{
        return invoiceDetailsService.getPage(databaseId, null)
    }

}