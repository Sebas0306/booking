package com.qts.erp.fox.models.room_type

import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Pagination

data class RoomTypeModel (
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null
)

data class RoomTypeFilterInput(
    var id: String? = null,
    var name: String? = null,
    var pagination: Pagination? = null):QueryFilterInterface