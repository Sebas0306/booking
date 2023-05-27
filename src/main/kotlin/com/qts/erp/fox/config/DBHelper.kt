package com.qts.erp.fox.config

import com.google.gson.Gson
import com.qts.erp.fox.QtsErpFoxApiApplication
import com.qts.erp.fox.config.enums.DatabaseDrivers
import com.qts.erp.fox.dto.accounts.DatabaseConnectionInfoDTO
import com.qts.erp.fox.services.accounts.DBService
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.sql2o.Sql2o
import java.time.Instant
import java.util.*

@Component
class DBHelper (private val dbService: DBService) {
    companion object {
        private val gson = Gson() // Flat Format
        private val logger : Logger = LoggerFactory.getLogger(DBHelper::class.java)
    }
    fun registerConnection (databaseId: String, dbConnectionInfo: DatabaseConnectionInfoDTO?) : Boolean {
        val dbClient : DatabaseConnectionInfoDTO = dbConnectionInfo ?: dbService.getDatabaseConnectionInfo(databaseId)
        logger.info("retrieved db : ${gson.toJson(dbClient)}")
        val props = Properties()

        // Si el model no presenta driver, se asume MSSQL, eso significa que cualquier conecxion con un driver diferente, debe ser provista en el DatabaseConnectionInfoDTO
        if (dbClient.driver == null)  dbClient.driver = DatabaseDrivers.MSSQL

        // Common engine properties
        props.setProperty("dataSourceClassName", dbClient.driver!!.value)
        props.setProperty("dataSource.user", dbClient.user)
        props.setProperty("dataSource.password", dbClient.pwd)
        props.setProperty("dataSource.databaseName", dbClient.database)
        props.setProperty("dataSource.portNumber", dbClient.port.toString())
        props.setProperty("dataSource.serverName", dbClient.host)

        // MSSQL Properties
        if (DatabaseDrivers.MSSQL == dbClient.driver) props.setProperty("dataSource.integratedSecurity", false.toString())
        if (DatabaseDrivers.MSSQL == dbClient.driver) props.setProperty("dataSource.encrypt", false.toString())
        if (DatabaseDrivers.MSSQL == dbClient.driver) props.setProperty("dataSource.trustServerCertificate", false.toString())
        val config = HikariConfig(props)
        val ds = HikariDataSource(config)
        val db : Sql2o = Sql2o(ds)

        QtsErpFoxApiApplication.dbPool[databaseId] = QtsErpFoxApiApplication.Companion.DatabaseContainer(lastRead = Instant.now(), dbClient = db)
        return true
    }
}