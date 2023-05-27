package com.qts.erp.fox.dto.accounts

import com.qts.erp.fox.config.enums.DatabaseDrivers
import com.qts.erp.fox.interfaces.global.QueryFilterInterface

class DatabaseConnectionInfoDTO(var host: String, var port: Int, var user: String, var pwd: String, var database: String, var driver: DatabaseDrivers? = DatabaseDrivers.MSSQL)
{
    constructor(): this (
        host = "",
        port = 0,
        user = "",
        pwd = "",
        database = "",
        driver = DatabaseDrivers.MSSQL
    )
}