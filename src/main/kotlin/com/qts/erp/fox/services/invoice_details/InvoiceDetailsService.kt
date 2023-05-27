package com.qts.erp.fox.services.invoice_details

import com.fasterxml.jackson.core.type.TypeReference
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.interfaces.global.DTOInterface
import com.qts.erp.fox.interfaces.global.GenericDataAccessInterface
import com.qts.erp.fox.interfaces.global.QueryFilterInterface
import com.qts.erp.fox.models.invoice_details.InvoiceDetailsModel
import org.springframework.stereotype.Component

@Component
class InvoiceDetailsService : GenericDataAccessInterface<InvoiceDetailsModel>{
    override fun findById(databaseId: String, id: Int): InvoiceDetailsModel? {
        TODO("Not yet implemented")
    }

    override fun getList(databaseId: String, filter: QueryFilterInterface?): List<InvoiceDetailsModel?> {
        TODO("Not yet implemented")
    }

    override fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<InvoiceDetailsModel?>? {
        TODO("Not yet implemented")
    }

    override fun getPage(
        databaseId: String,
        filter: QueryFilterInterface?
    ): GenericDataAccessInterface.PageResultType<InvoiceDetailsModel> {
        val sql : String = """
            EXEC	[dbo].[sp_get_invoice_details_page] @filter = NULL
        """.trimIndent()
        getConnection(databaseId).open().use {con->
            val result : String = con.createQuery(sql).executeScalar(String::class.java)
            return mapper.readValue(result, object : TypeReference<GenericDataAccessInterface.PageResultType<InvoiceDetailsModel>>(){})
        }
    }

    override fun delete(databaseId: String, id: Int): InvoiceDetailsModel? {
        TODO("Not yet implemented")
    }

    override fun insert(databaseId: String, model: DTOInterface): InvoiceDetailsModel {
        val sql: String = """
            DECLARE @json NVARCHAR(MAX)
            SET @json = :json
            EXEC [dbo].[sp_create_invoice_details] @json
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con.createQuery(sql)
                .addParameter("json","{\"data\": ${mapper.writeValueAsString(model)}}" )
                .executeAndFetchFirst(InvoiceDetailsModel::class.java)
        }
    }

    override fun update(databaseId: String, model: DTOInterface): InvoiceDetailsModel {
        TODO("Not yet implemented")
    }

}