package com.qts.erp.fox.services.hotel

import com.fasterxml.jackson.core.type.TypeReference
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.models.hotel.HotelModel
import org.springframework.stereotype.Component

@Component
class HotelService : GenericDataAccessInterface<HotelModel>{
    override fun findById(databaseId: String, id: Int): HotelModel? {
        TODO("Not yet implemented")
    }

    override fun getList(databaseId: String, filter: QueryFilterInterface?): List<HotelModel?> {
        TODO("Not yet implemented")
    }

    override fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<HotelModel?>? {
        val sql: String = """
            DECLARE @ids VARCHAR(MAX)
            SET @ids = :ids
            EXEC [dbo].[sp_get_hotel_by_ids] @ids
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con.createQuery(sql)
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(HotelModel::class.java)
        }
    }

    override fun getPage(
        databaseId: String,
        filter: QueryFilterInterface?
    ): GenericDataAccessInterface.PageResultType<HotelModel> {
        val sql : String = """
            EXEC	[dbo].[sp_get_hotel_page] @filter = NULL
        """.trimIndent()
        getConnection(databaseId).open().use { con->
            val result: String = con.createQuery(sql).executeScalar(String::class.java)
            return mapper.readValue(result, object : TypeReference<GenericDataAccessInterface.PageResultType<HotelModel>>(){})
        }
    }

    override fun delete(databaseId: String, id: Int): HotelModel? {
        TODO("Not yet implemented")
    }

    override fun insert(databaseId: String, model: DTOInterface): HotelModel {
        TODO("Not yet implemented")
    }

    override fun update(databaseId: String, model: DTOInterface): HotelModel {
        TODO("Not yet implemented")
    }

}
