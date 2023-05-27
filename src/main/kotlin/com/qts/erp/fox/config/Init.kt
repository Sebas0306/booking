package com.qts.erp.fox.config

import com.google.gson.Gson
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.database_accounts_connection_key
import com.qts.erp.fox.config.enums.DatabaseDrivers
import com.qts.erp.fox.dto.accounts.DatabaseConnectionInfoDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class Init(private val dbHelper: DBHelper) : ApplicationRunner {

    // Connection to accounts database
    private final val dbConnectionProperties : DatabaseConnectionInfoDTO = DatabaseConnectionInfoDTO(
        host = "develop.cr9rp8hmiz60.us-east-1.rds.amazonaws.com",
        port = 5432,
        user = "postgres",
        pwd = "hszEYzu8u99HN1HvT3BW",
        database = "qts_account",
        driver = DatabaseDrivers.POSTGRESQL)

    companion object {
        private val logger : Logger = LoggerFactory.getLogger(Init::class.java)
        private val gson = Gson() // Flat Format
    }

    override fun run(args: ApplicationArguments?) {
        dbHelper.registerConnection(database_accounts_connection_key, dbConnectionProperties)
        logger.info("Application Init executed")
    }
}