package com.qts.erp.fox.models.accounts

class DatabaseModel (
    var host: String,
    var port: Int,
    var name: String,
    var user: String,
    var pwd: String) {
    lateinit var id: String
}

