package com.qts.erp.fox.datafetchers.invoice

import com.netflix.graphql.dgs.*
import com.qts.erp.fox.dataloaders.invoice.InvoiceItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.extra.ExtraFilterInput
import com.qts.erp.fox.models.invoice.InvoiceFilterInput
import com.qts.erp.fox.models.invoice.InvoiceModel
import com.qts.erp.fox.models.invoice_details.InvoiceDetailsModel
import com.qts.erp.fox.services.invoice.InvoiceService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture

@DgsComponent
class InvoiceDataFetcher (private val invoiceService: InvoiceService) {
    @DgsQuery
    fun invoicePage(@RequestHeader databaseId:String, @InputArgument filter: InvoiceFilterInput?): GenericDataAccessInterface.PageResultType<InvoiceModel>{
        return invoiceService.getPage(databaseId, filter)
    }
    @DgsData(parentType = "InvoiceDetails", field = "invoice")
    fun getInvoiceOnInvoiceDetails (dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<InvoiceModel?>> {
        val dataLoader: DataLoader<Int?, List<InvoiceModel?>> = dfe.getDataLoader(InvoiceItemByIdDataLoader::class.java)
        val invoiceDetail: InvoiceDetailsModel = dfe.getSource()
        return dataLoader.load(invoiceDetail.idInvoice)
    }
}