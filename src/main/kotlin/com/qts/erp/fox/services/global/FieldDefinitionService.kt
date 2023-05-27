package com.qts.erp.fox.services.global

import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.mapper
import com.qts.erp.fox.models.global.FieldDefinitionModel
import org.springframework.stereotype.Component

@Component
class FieldDefinitionService {
    fun getFieldsDefinitionsByTableNames(databaseId: String, tables:List<String>):List<FieldDefinitionModel> {
        val sql = """
        execute sp_get_field_definition_by_table_name @tables = :tables
        """.trimIndent()
        getConnection(databaseId).open().use { con ->
            return con.createQuery(sql)
                .addParameter("tables", mapper.writeValueAsString(tables))
                .executeAndFetch(FieldDefinitionModel::class.java)
        }
    }
}