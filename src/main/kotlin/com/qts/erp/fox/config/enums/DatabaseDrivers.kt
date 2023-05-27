package com.qts.erp.fox.config.enums

enum class DatabaseDrivers (val value: String) {
    POSTGRESQL("org.postgresql.ds.PGSimpleDataSource"),
    MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDataSource")
}