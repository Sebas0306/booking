package com.qts.erp.fox.utils

import com.fasterxml.jackson.annotation.JsonProperty

data class Pagination(
    val page: Int = 1,
    @get:JsonProperty("pageSize")
    val pageSize: Int = 20) {
}