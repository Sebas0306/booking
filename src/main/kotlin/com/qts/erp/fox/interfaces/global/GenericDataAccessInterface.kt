package com.qts.erp.fox.interfaces.global

import com.fasterxml.jackson.annotation.JsonProperty

interface GenericDataAccessInterface<T> {
    fun findById(databaseId: String, id: Int): T?
    fun getList(databaseId: String, filter: QueryFilterInterface?): List<T?>
    fun getListByIds(databaseId: String, ids: MutableList<Int?>?): List<T?>?
    fun getPage(databaseId: String, filter: QueryFilterInterface?): PageResultType<T>
    fun delete(databaseId: String, id: Int): T?
    fun insert(databaseId: String, model: DTOInterface): T
    fun update(databaseId: String, model: DTOInterface): T
    class PageResultType<T> (@JsonProperty var count: Int? = null, @JsonProperty var rows: List<T> = listOf()) {
        constructor() : this(count = 0, rows = listOf())
    }
}

