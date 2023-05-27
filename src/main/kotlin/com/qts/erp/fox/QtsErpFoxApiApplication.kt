package com.qts.erp.fox

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.sql2o.Sql2o
import java.time.Instant
import java.util.*


@SpringBootApplication
class QtsErpFoxApiApplication {

	companion object{
		const val database_accounts_connection_key : String = "accounts_connection"
		val logger : Logger = LoggerFactory.getLogger(QtsErpFoxApiApplication::class.java)
		val dbPool : SortedMap<String, DatabaseContainer> = sortedMapOf()
		val mapper : ObjectMapper = JsonMapper.builder() // or different mapper for other format
			.addModule(ParameterNamesModule()) 
			.addModule(Jdk8Module())
			.addModule(JavaTimeModule())
			.build()
		class DatabaseContainer(var lastRead : Instant, val dbClient: Sql2o)
		fun getConnection(databaseId: String): Sql2o {
			return dbPool[databaseId]!!.dbClient
		}
	}
}

fun main(args: Array<String>) {
	runApplication<QtsErpFoxApiApplication>(*args)
}
