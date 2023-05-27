package com.qts.erp.fox.models.extra

import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Pagination

data class ExtraModel(
    var id: Int? = null,
    var name: String? = null,
    var cost: Int? = 0,
    var description: String? = null
)

data class ExtraPageResponseType(var count: Int? = 0, var rows: List<ExtraModel>? = listOf())

data class ExtraFilterInput(
    var id: String? = null,
    var name: String? = null,
    var pagination: Pagination? = null): QueryFilterInterface