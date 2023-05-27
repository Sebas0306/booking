package com.qts.erp.fox.mutations.invoice_details

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.models.invoice_details.InvoiceDetailsCreateInput
import com.qts.erp.fox.models.invoice_details.InvoiceDetailsModel
import com.qts.erp.fox.services.invoice_details.InvoiceDetailsService
import org.springframework.web.bind.annotation.RequestHeader

@DgsComponent
class InvoiceDetailsMutation(private val invoiceDetailsService: InvoiceDetailsService) {

    @DgsMutation
    fun createInvoiceDetails(@RequestHeader databaseId: String, @InputArgument data: InvoiceDetailsCreateInput): InvoiceDetailsModel{
        return invoiceDetailsService.insert(databaseId, data)
    }
}