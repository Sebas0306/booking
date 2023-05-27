package com.qts.erp.fox.config

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
class AppContextBuilder : DgsCustomContextBuilderWithRequest<AppContext?> {
    override fun build(extensions: Map<String, Any>?, headers: HttpHeaders?, webRequest: WebRequest?): AppContext? {
        val databaseId = headers?.get("databaseId")?.get(0)
        return AppContext(databaseId)
    }
}

class AppContext(val databaseId: String?)