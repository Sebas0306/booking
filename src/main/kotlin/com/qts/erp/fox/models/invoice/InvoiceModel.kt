package com.qts.erp.fox.models.invoice

import com.fasterxml.jackson.annotation.JsonProperty
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Pagination
import java.io.Serializable

data class InvoiceModel(
    var id: Int? = null,
    @get:JsonProperty("invoiceDate")
    var invoiceDate: String? = null
)
{
    var idClient: Int? = null
}

data class InvoiceFilterInput(
    var id: String? = null,
    var pagination: Pagination? = null): QueryFilterInterface

data class InvoiceCreateInput(
    var invoiceDate: String? = null,
    var idClient: Int? = null
): DTOInterface, Serializable

