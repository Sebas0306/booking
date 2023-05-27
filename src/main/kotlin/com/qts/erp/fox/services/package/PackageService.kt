package com.qts.erp.fox.services.`package`

import com.fasterxml.jackson.core.type.TypeReference
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.models.`package`.PackageModel
import org.springframework.stereotype.Component

@Component
class PackageService : GenericDataAccessInterface<PackageModel>{
    override fun findById(databaseId: String, id: Int): PackageModel? {
        TODO("Not yet implemented")
    }

    override fun getList(databaseId: String, filter: QueryFilterInterface?): List<PackageModel?> {
        TODO("Not yet implemented")
    }

    override fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<PackageModel?>? {
        val sql: String = """
            DECLARE @ids VARCHAR(MAX)
            SET @ids = :ids            
            EXEC	[dbo].[sp_get_package_by_ids] @ids
        """.trimIndent()
            getConnection(databaseId).open().use { con->
                return con.createQuery(sql)
                    .addParameter("ids", mapper.writeValueAsString(ids))
                    .executeAndFetch(PackageModel::class.java)
            }
    }

    override fun getPage(
        databaseId: String,
        filter: QueryFilterInterface?
    ): GenericDataAccessInterface.PageResultType<PackageModel> {
        val sql : String = """
            EXEC	[dbo].[sp_get_package_page] @filter = :filter
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            val result : String = con.createQuery(sql)
                .addParameter("filter", if (filter == null) "{}" else mapper.writeValueAsString(filter))
                .executeScalar(String::class.java)
            return mapper.readValue(result, object : TypeReference<GenericDataAccessInterface.PageResultType<PackageModel>>() {})
        }
    }

    override fun delete(databaseId: String, id: Int): PackageModel? {
        TODO("Not yet implemented")
    }

    override fun insert(databaseId: String, model: DTOInterface): PackageModel {
        TODO("Not yet implemented")
    }

    override fun update(databaseId: String, model: DTOInterface): PackageModel {
        TODO("Not yet implemented")
    }

}