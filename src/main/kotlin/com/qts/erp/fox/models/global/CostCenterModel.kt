package com.qts.erp.fox.models.global

import com.fasterxml.jackson.annotation.JsonProperty
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.utils.Order
import com.qts.erp.fox.utils.Pagination

data class CostCenterModel (
    val id:         Int,
    val code:       String?,
    var name:       String? = "",
    var address:    String? = "",
    var phone1:     String? = "",
    var phone2:     String? = "",
    @get:JsonProperty("cellPhone1")
    var cellPhone1: String? = "",
    @get:JsonProperty("cellPhone2")
    var cellPhone2: String? = "",
    @get:JsonProperty("tradeName")
    var tradeName:  String? = "",
    @get:JsonProperty("shortName")
    var shortName:  String? = "",
    @get:JsonProperty("displayOrder")
    var displayOrder: Int   = 0,
    @get:JsonProperty("priceListIncludeTax")
    var priceListIncludeTax: Boolean = false,
    @get:JsonProperty("applyTax")
    var applyTax: Boolean = false
) : DTOInterface {
        // NOT Domain properties
        @get:JsonProperty("costCenterGroupId")
        val costCenterGroupId: Int? = null
        @get:JsonProperty("sellerId")
        val sellerId: Int? = null
        val username: String? = null
        constructor():this(
            id = 0,
            code = "",
            name = "",
            address = "",
            phone1 = "",
            phone2 = "",
            cellPhone1 = "",
            cellPhone2 = "",
            tradeName = "",
            shortName  = "",
            displayOrder = 0,
            priceListIncludeTax = false,
            applyTax  = false
        )
}

data class CostCentersFilterInput(
    var id: Int? = null,
    var name: String? = null) : QueryFilterInterface

data class CostCenterFilterInput(
    var id: String? = null,
    var name: String? = null,
    var order: List<Order?>? = null,
    var pagination: Pagination? = null,
    var syncCostCenterCode: String? = null
): QueryFilterInterface