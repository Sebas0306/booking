package com.qts.erp.fox.models.`package`

import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Pagination

data class PackageModel(
    var id: Int? = null,
    var name: String? = null,
    var cost: Int? = 0,
    var description: String? = null
)

data class PackagePageResponseType (var count: Int? = 0, var rows: List<PackageModel>? = listOf())

data class PackageFilterInput(
    var id: String? = null,
    var name: String? = null,
    var pagination: Pagination? = null): QueryFilterInterface