package com.qts.erp.fox.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.util.StringUtils
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.stream.Stream
import javax.servlet.Filter
import javax.servlet.http.HttpServletRequest

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilterConfiguration {

    @Bean
    fun corsFilter(): FilterRegistrationBean<Filter>? {
        val filterRegistrationBean = FilterRegistrationBean<Filter>()
            filterRegistrationBean.filter = CorsFilter(CorsConfigurationSource { request: HttpServletRequest ->
            val origin = request.getHeader(HttpHeaders.ORIGIN)
            val configuration = CorsConfiguration()
            configuration.addAllowedOrigin(origin)
            val accessControlRequestHeaders =
                request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS)
            if (StringUtils.hasText(accessControlRequestHeaders)) {
                Stream.of(
                    *accessControlRequestHeaders.split(",".toRegex()).toTypedArray()
                ).map { obj: String -> obj.trim { it <= ' ' } }
                    .distinct()
                    .forEach { allowedHeader: String? ->
                        configuration.addAllowedHeader(
                            allowedHeader!!
                        )
                    }
            }
            configuration.addExposedHeader("*")
            configuration.allowCredentials = true
            configuration.allowedMethods = listOf(
                "GET",
                "HEAD",
                "POST",
                "PUT",
                "PATCH",
                "DELETE",
                "OPTIONS",
                "TRACE"
            )
            configuration
        })
        filterRegistrationBean.addUrlPatterns("/*")
        filterRegistrationBean.order = Int.MIN_VALUE // Ensure first execution
        return filterRegistrationBean
    }
}