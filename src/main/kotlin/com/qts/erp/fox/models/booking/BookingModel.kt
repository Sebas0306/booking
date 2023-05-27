package com.qts.erp.fox.models.booking

import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Pagination
import java.io.Serializable

data class BookingModel(
    var id: Int? = null,
    var children: Int? = null,
    var adult: Int? = null,
    var description: String? = null
)
{
    var idClient: Int? = null
    var idRoom: Int? = null
    var idPackage: Int? = null
    var idExtra: Int? = null
}

data class BookingFilterInput(
    var id: String? = null,
    var pagination: Pagination
): QueryFilterInterface

data class BookingCreateInput(
    var children: Int? = null,
    var adult: Int,
    var description: String? = null,
    var idClient: Int,
    var idRoom: Int,
    var idPackage: Int? = null,
    var idExtra: Int? = null
):DTOInterface, Serializable