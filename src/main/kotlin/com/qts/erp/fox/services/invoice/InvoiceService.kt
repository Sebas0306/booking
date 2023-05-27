package com.qts.erp.fox.services.invoice

import com.fasterxml.jackson.core.type.TypeReference
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.logger
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.models.invoice.InvoiceModel
import org.springframework.stereotype.Component

@Component
class InvoiceService : GenericDataAccessInterface<InvoiceModel> {
    override fun findById(databaseId: String, id: Int): InvoiceModel? {
        TODO("Not yet implemented")
    }

    override fun getList(databaseId: String, filter: QueryFilterInterface?): List<InvoiceModel?> {
        TODO("Not yet implemented")
    }

    override fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<InvoiceModel?>? {
        val sql: String? = """
            DECLARE @ids VARCHAR(MAX)
            SET @ids = :ids
            EXEC    [dbo].[sp_get_invoice_by_ids] @ids
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con.createQuery(sql)
                .addParameter("ids", mapper.writeValueAsString(ids))
                .executeAndFetch(InvoiceModel::class.java)
        }
    }

    override fun getPage(
        databaseId: String,
        filter: QueryFilterInterface?
    ): GenericDataAccessInterface.PageResultType<InvoiceModel> {
        val sql: String = """
            EXEC    [dbo].[sp_get_invoice_page] @filter = :filter
        """.trimIndent()
            getConnection(databaseId).open().use {con->
                val result : String = con.createQuery(sql)
                    .addParameter("filter", if (filter == null) "{}" else mapper.writeValueAsString(filter))
                    .executeScalar(String::class.java)
                return mapper.readValue(result, object : TypeReference<GenericDataAccessInterface.PageResultType<InvoiceModel>>() {})
            }
    }

    override fun delete(databaseId: String, id: Int): InvoiceModel? {
        TODO("Not yet implemented")
    }

    override fun insert(databaseId: String, model: DTOInterface): InvoiceModel {
        val sql: String = """
            DECLARE @json NVARCHAR(MAX)
            SET @json = :json
            EXEC	[dbo].[sp_create_invoice] @json
        """.trimIndent()
        logger.info("{\"data\": ${mapper.writeValueAsString(model)}}")
        getConnection(databaseId).open().use { con->
            return con.createQuery(sql)
                .addParameter("json", "{\"data\": ${mapper.writeValueAsString(model)}}")
                .executeAndFetchFirst(InvoiceModel::class.java)
        }
    }

    override fun update(databaseId: String, model: DTOInterface): InvoiceModel {
        TODO("Not yet implemented")
    }
}