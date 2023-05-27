package com.qts.erp.fox.inputs

class DatabaseConnectionPropertiesInput (var host: String, var port: Int, var user: String, var pwd: String, var database: String) {
    constructor(): this(
        host = "",
        port = 0,
        user = "",
        pwd = "",
        database = ""
    )
}