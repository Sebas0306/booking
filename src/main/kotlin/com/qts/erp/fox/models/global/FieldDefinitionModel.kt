package com.qts.erp.fox.models.global

data class FieldDefinitionModel (val tableName: String, val columnName: String, val length: Int)
{
    constructor(): this (
        tableName = "",
        columnName = "",
        length = 0
    )
}