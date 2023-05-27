package com.qts.erp.fox.services.accounts

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.database_accounts_connection_key
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.getConnection
import com.qts.erp.fox.dto.accounts.DatabaseConnectionInfoDTO
import com.qts.erp.fox.models.accounts.DBModel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DBService() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(DBService::class.java)
        val gson = Gson()
    }

    fun getDatabaseListByServerIds(ids: MutableSet<Int?>?) : List<DBModel> {
        val sql = """
        SELECT * FROM get_databases_by_server_ids ('{${ids?.joinToString(separator = ",") }}');
        """.trimIndent()
        getConnection(database_accounts_connection_key).open().use { con ->
            return con.createQuery(sql)!!.executeAndFetch(DBModel::class.java)
        }
    }

    /***
     * Get Database Connection Info for provided database Key
     * @author Aldrin Ronco C
     * @param dbKey: Database key to be fetched
     * @return DatabaseConnectionInfoDTO instance
     */

    fun getDatabaseConnectionInfo(dbKey: String) : DatabaseConnectionInfoDTO {
        val sql = """
        SELECT * FROM get_database_connection_info(:dbKey::uuid);
        """.trimIndent()
        getConnection(database_accounts_connection_key).open().use { con ->
            return con.createQuery(sql)!!
                .addParameter("dbKey", dbKey)
                .executeAndFetchFirst(DatabaseConnectionInfoDTO::class.java)
        }
    }
}