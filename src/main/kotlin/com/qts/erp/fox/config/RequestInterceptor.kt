@file:Suppress("DuplicatedCode")

package com.qts.erp.fox.config

import com.google.gson.Gson
import com.qts.erp.fox.QtsErpFoxApiApplication.Companion.dbPool
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestInterceptor(private val dbHelper: DBHelper) : HandlerInterceptor{
    var databaseId: String? = null
    companion object {
        private val gson = Gson() // Flat Format
        private val logger : Logger = LoggerFactory.getLogger(RequestInterceptor::class.java)
    }
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        databaseId = request.getHeader("DatabaseId")
        return if (databaseId == null) { // Login request don't need databaseId
            true
        } else if (dbPool.containsKey(databaseId)) {
            true
        } else {
            if (dbHelper.registerConnection(databaseId!!, null))
            {
                logger.info("the database with id : $databaseId has been registered")
                true
            } else { false }
        }
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView? )
    {
        // Update lastRead Map Property
        databaseId = request.getHeader("DatabaseId")
        if (databaseId != null) {
            if (dbPool.containsKey(databaseId)) {
                val dbToUpdate = dbPool[databaseId]
                dbToUpdate?.lastRead = Instant.now()
                dbPool[databaseId] = dbToUpdate
            }
        }
    }
}

@Configuration
public class WebMvcConfig(private val dbHelper: DBHelper) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(RequestInterceptor(dbHelper))
    }
}
