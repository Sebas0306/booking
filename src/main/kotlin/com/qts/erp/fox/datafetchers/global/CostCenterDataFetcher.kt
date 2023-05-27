package com.qts.erp.fox.datafetchers.global

import com.google.gson.Gson
import com.netflix.graphql.dgs.*
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.models.global.CostCenterFilterInput
import com.qts.erp.fox.services.global.CostCenterService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestHeader
import com.qts.erp.fox.models.global.CostCenterModel
import com.qts.erp.fox.models.global.CostCentersFilterInput

@DgsComponent
class CostCenterDataFetcher(private val costCenterService: CostCenterService) {
    companion object {
        private val logger = LoggerFactory.getLogger(CostCenterDataFetcher::class.java)
        private val gson = Gson()
    }

    @DgsQuery
    fun costCentersPage(@RequestHeader databaseId: String, @InputArgument filter: CostCenterFilterInput?): GenericDataAccessInterface.PageResultType<CostCenterModel> {
        return costCenterService.getPage(databaseId, filter = filter)
    }

    /**
     * @return a List<CostCenterModel>
     * @param filter: a filter criteria to fetch data
     * @author: Aldrin Ronco C.
     */
    @DgsQuery
    fun costCenters(@RequestHeader databaseId: String, @InputArgument filter: CostCentersFilterInput?): List<CostCenterModel> {
        return costCenterService.getList(databaseId, filter)
    }

    /**
     * @return a <CostCenterModel>
     * @param id: costCenter id to fetch
     * @author: Aldrin Ronco C.
     */
    @DgsQuery
    fun costCenter(@RequestHeader databaseId: String, @InputArgument id: Int): CostCenterModel {
        return costCenterService.findById(databaseId, id)
    }

}