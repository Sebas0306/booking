package com.qts.erp.fox.models.invoice_details

import com.fasterxml.jackson.annotation.JsonProperty
import com.qts.erp.fox.interfaces.global.DTOInterface
import java.io.Serializable

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

data class InvoiceDetailsCreateInput(
    var cost: Int,
    var payMethod: String,
    var idBooking: Int,
    var idInvoice: Int
): DTOInterface, Serializable