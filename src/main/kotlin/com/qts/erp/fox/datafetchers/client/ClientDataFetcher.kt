package com.qts.erp.fox.datafetchers.client

import com.netflix.graphql.dgs.*
import com.qts.erp.fox.dataloaders.client.ClientItemByIdDataLoader
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.models.client.ClientFilterInput
import com.qts.erp.fox.models.client.ClientModel
import com.qts.erp.fox.models.invoice.InvoiceModel
import com.qts.erp.fox.services.client.ClientService
import org.dataloader.DataLoader
import org.springframework.web.bind.annotation.RequestHeader
import java.util.concurrent.CompletableFuture
import javax.xml.crypto.Data

@DgsComponent
class ClientDataFetcher(private val clientService: ClientService){
    @DgsQuery
    fun clientPage(@RequestHeader databaseId : String, @InputArgument filter: ClientFilterInput?):GenericDataAccessInterface.PageResultType<ClientModel>{
        return clientService.getPage(databaseId, filter)
    }
    @DgsData(parentType = "Booking", field = "client")
    fun getClientOnBooking(dfe: DgsDataFetchingEnvironment, @RequestHeader databaseId: String): CompletableFuture<List<ClientModel?>>{
        val dataLoader: DataLoader<Int?, List<ClientModel?>> = dfe.getDataLoader(ClientItemByIdDataLoader::class.java)
        val booking: BookingModel = dfe.getSource()
        return dataLoader.load(booking.idClient)
    }

    @DgsData (parentType = "Invoice", field = "client")
    fun getClientOnInvoice(dfe:DgsDataFetchingEnvironment, @RequestHeader databaseId: String):CompletableFuture<List<ClientModel?>>{
        val dataLoader: DataLoader<Int?, List<ClientModel?>> = dfe.getDataLoader(ClientItemByIdDataLoader::class.java)
        val invoice: InvoiceModel = dfe.getSource()
        return  dataLoader.load(invoice.idClient)
    }
}