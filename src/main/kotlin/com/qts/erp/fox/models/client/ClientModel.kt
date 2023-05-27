package com.qts.erp.fox.models.client

import com.fasterxml.jackson.annotation.JsonProperty
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Pagination
import java.io.Serializable

data class ClientModel(
    var id: Int? = null,
    var name: String? = null,
    @get:JsonProperty("idNumber")
    var idNumber: String? = null,
    @get:JsonProperty("phNumber")
    var phNumber: String? = null,
    var email: String? = null
)


data class ClientFilterInput(
    var id: String? = null,
    var name: String? = null,
    var pagination: Pagination? = null) : QueryFilterInterface

data class ClientCreateInput(
    var name: String,
    var idNumber: String,
    var phNumber: String,
    var email: String
) : DTOInterface, Serializable