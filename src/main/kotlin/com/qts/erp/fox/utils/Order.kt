package com.qts.erp.fox.utils

import com.fasterxml.jackson.annotation.JsonProperty

data class Order(
    val type: String,
    val field: String
) {
      constructor(): this(
          type = "",
          field = ""
      )
}