package com.qts.erp.fox.services.global

import com.fasterxml.jackson.core.type.TypeReference
import com.google.gson.Gson
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.dbPool
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.models.global.CostCenterModel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CostCenterService : GenericDataAccessInterface<CostCenterModel> {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CostCenterService::class.java)
        private val gson = Gson()
    }

    /**
     * *getListBySellerIds*
     * @author Aldrin Ronco C.
     * @param databaseId: DataBase connection in dbPool
     * @ids List of sellers Ids
     * @return The function return a List<CostCenterModel> based on the sellers ids provided
     */
    fun getListBySellerIds(databaseId: String, ids: MutableSet<Int?>?): List<CostCenterModel?> {
        val sql : String = """
            DECLARE @ids VARCHAR(MAX)
            SET @ids = :ids
            EXEC sp_get_cost_centers_by_seller_ids @ids
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con?.createQuery(sql)!!
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(CostCenterModel::class.java)
        }
    }

    fun getListByUserName(databaseId: String, usernames: MutableSet<String?>?): List<CostCenterModel?> {
        val sql : String = """
            DECLARE @usernames VARCHAR(MAX)
            SET @usernames = :usernames
            EXEC sp_get_cost_center_list_by_user_names @usernames
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con?.createQuery(sql)!!
                .addParameter("usernames", mapper.writeValueAsString(usernames))
                .executeAndFetch(CostCenterModel::class.java)
        }
    }

    fun getOrdersCostCentersListBySellerIds(databaseId: String, ids:MutableSet<Int?>?): List<CostCenterModel?> {
        val sql = """
            DECLARE @ids VARCHAR(MAX)
            SET @ids = :ids
            EXEC [dbo].[sp_get_cost_centers_for_orders_by_seller_ids] @ids
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con?.createQuery(sql)!!
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(CostCenterModel::class.java)
        }
    }

    override fun findById(databaseId: String, id: Int): CostCenterModel {
        val sql = """
        DECLARE	@id int
        SET @id = :id
        EXEC [dbo].[sp_get_cost_center_by_id] @id
        """.trimIndent();
        getConnection(databaseId).open().use { con ->
            return con?.createQuery(sql)!!
                .addParameter("id", id)
                .executeAndFetchFirst(CostCenterModel::class.java)
        }
     }

    override fun getList(databaseId: String, filter: QueryFilterInterface?): List<CostCenterModel> {
        val sql : String = """
        DECLARE @filter VARCHAR(MAX)
        SET @filter = '${gson.toJson(filter)}'           
        EXEC sp_get_cost_centers @filter  
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con?.createQuery(sql)!!.executeAndFetch(CostCenterModel::class.java)
        }
    }

    override fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<CostCenterModel?> {
        val sql =""" 
            DECLARE	@ids varchar(max)
            set @ids = :ids
            EXEC [dbo].[sp_get_cost_center_by_ids] @ids
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con.createQuery(sql)
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(CostCenterModel::class.java)
        }
    }

    override fun delete(databaseId: String, id: Int): CostCenterModel {
        TODO("Not yet implemented")
    }

    override fun insert(databaseId: String, model: DTOInterface): CostCenterModel {
        TODO("Not yet implemented")
    }

    override fun update(databaseId: String, model: DTOInterface): CostCenterModel {
        TODO("Not yet implemented")
    }


    override fun getPage(
        databaseId: String,
        filter: QueryFilterInterface?
    ): GenericDataAccessInterface.PageResultType<CostCenterModel> {
        val sql ="""
            DECLARE @filter VARCHAR(MAX)
            SET @filter = :filter
            EXEC [dbo].[sp_get_cost_centers_page] @filter
        """.trimIndent()

        getConnection(databaseId).open().use { con ->
            val result: String = con.createQuery(sql)
                .addParameter("filter",(if(filter == null) "{}" else mapper.writeValueAsString(filter)))
                .executeScalar(String::class.java)
            return mapper.readValue(result,
                object : TypeReference<GenericDataAccessInterface.PageResultType<CostCenterModel>>() {})
        }
    }

}

