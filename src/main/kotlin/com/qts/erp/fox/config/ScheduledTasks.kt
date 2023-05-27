package com.qts.erp.fox.config

import com.qts.erp.fox.QtsErpFoxApiApplication
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.database_accounts_connection_key
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.dbPool
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.time.Instant

private const val MINUTE = 60000L // Milliseconds
private const val HOUR = (MINUTE*60) // Seconds
private const val GARBAGE_COLLECTOR_FREQUENCY = (HOUR*1)

@Configuration
@EnableScheduling
class ScheduledTasks () {
    @Scheduled(fixedRate = GARBAGE_COLLECTOR_FREQUENCY)
    fun dbPoolGarbageCollector() {
        val dbIterator = dbPool.iterator()
        while (dbIterator.hasNext()) {
            val dbItem: MutableMap.MutableEntry<String, QtsErpFoxApiApplication.Companion.DatabaseContainer> = dbIterator.next()
            val elapsedSeconds = (Instant.now().toEpochMilli()-dbItem.value.lastRead.toEpochMilli())/1000
            logger.info("executing dbPool garbage collector every $GARBAGE_COLLECTOR_FREQUENCY seconds")
            if (elapsedSeconds>=(GARBAGE_COLLECTOR_FREQUENCY) && (dbItem.key !== database_accounts_connection_key)) { // Si ha pasado por lo menos una hora desde el ultimo acceso a la base de datos, remuevo la conexion del pool
                logger.info("removing database with id : ${dbItem.key} for inactivity")
                dbIterator.remove()
            }
        }
    }
    companion object {
        val logger : Logger = LoggerFactory.getLogger(ScheduledTasks::class.java)
    }
}
