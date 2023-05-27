package com.qts.erp.fox.models.invoice_details

import com.fasterxml.jackson.annotation.JsonProperty

data class InvoiceDetailsModel(
    var id: String? = null,
    var cost: Int? = 0,
    @get:JsonProperty("payMethod")
    var payMethod: String? = null
)
{
    var idBooking: Int? = null
    var idInvoice: Int? = null
}
