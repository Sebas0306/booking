package com.qts.erp.fox.models.room

import com.qts.erp.fox.interfaces.global.QueryFilterInterface


data class RoomModel(
    var id: Int? = null,
    var number: Int? = null,
    var floor: Int? = null,
    var capacity: Int? = null,
    var avail: Int? = null,
    var cost: Int? = null,
    var description: String? = null,
) {
    var idHotel: Int? = null
    var idRoomType: Int? = null
}

data class RoomFilterInput(
    var id: String? = null,
    var number: Int? = null,
    var floor: Int? = null,
    var capacity: Int? = null,
    var avail: Int? = null
): QueryFilterInterface