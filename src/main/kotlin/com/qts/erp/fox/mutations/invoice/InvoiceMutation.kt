package com.qts.erp.fox.mutations.invoice

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.models.invoice.InvoiceCreateInput
import com.qts.erp.fox.models.invoice.InvoiceModel
import com.qts.erp.fox.services.invoice.InvoiceService
import org.springframework.web.bind.annotation.RequestHeader

@DgsComponent
class InvoiceMutation (private val invoiceService: InvoiceService) {

    @DgsMutation
    fun createInvoice(@RequestHeader databaseId: String, @InputArgument data: InvoiceCreateInput): InvoiceModel{
       return invoiceService.insert(databaseId, data)
    }
}