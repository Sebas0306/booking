package com.qts.erp.fox.services.room

import com.fasterxml.jackson.core.type.TypeReference
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.models.room.RoomModel
import org.springframework.stereotype.Component

@Component
class RoomService : GenericDataAccessInterface<RoomModel> {
    override fun findById(databaseId: String, id: Int): RoomModel? {
        TODO("Not yet implemented")
    }

    override fun getList(databaseId: String, filter: QueryFilterInterface?): List<RoomModel?> {
        TODO("Not yet implemented")
    }

    override fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<RoomModel?>? {
        val sql: String = """
            DECLARE @ids VARCHAR(MAX)
            SET @ids = :ids
            EXEC    [dbo].[sp_get_room_by_ids] @ids         
        """.trimIndent()
        getConnection(databaseId).open().use {con->
            return con.createQuery(sql)
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(RoomModel::class.java)
        }
    }

    fun getRoomsByHotelIds(databaseId: String, ids: MutableSet<Int?>?): List<RoomModel?> {
        val sql: String = """
         DECLARE @ids VARCHAR(MAX)
         SET @ids = :ids
         EXEC [dbo].[sp_get_rooms_by_hotel_ids] @ids 
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con.createQuery(sql)
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(RoomModel::class.java)
        }
    }

    override fun getPage(
        databaseId: String,
        filter: QueryFilterInterface?
    ): GenericDataAccessInterface.PageResultType<RoomModel> {
       val sql : String = """
           EXEC	   [dbo].[sp_get_room_page] @filter = :filter
       """.trimIndent()
        getConnection(databaseId).open().use { con ->
            val result: String = con.createQuery(sql)
                .addParameter("filter", if (filter == null) "{}" else mapper.writeValueAsString(filter))
                .executeScalar(String::class.java)
            return mapper.readValue(result, object : TypeReference<GenericDataAccessInterface.PageResultType<RoomModel>>(){})
        }
    }

    override fun delete(databaseId: String, id: Int): RoomModel? {
        TODO("Not yet implemented")
    }

    override fun insert(databaseId: String, model: DTOInterface): RoomModel {
        TODO("Not yet implemented")
    }

    override fun update(databaseId: String, model: DTOInterface): RoomModel {
        TODO("Not yet implemented")
    }

}