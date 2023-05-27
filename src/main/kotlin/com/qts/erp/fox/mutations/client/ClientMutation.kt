package com.qts.erp.fox.mutations.client

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.models.client.ClientCreateInput
import com.qts.erp.fox.models.client.ClientModel
import com.qts.erp.fox.services.client.ClientService
import org.springframework.web.bind.annotation.RequestHeader

@DgsComponent
class ClientMutation(private val clientService: ClientService) {

    @DgsMutation
    fun createClient(@RequestHeader databaseId: String, @InputArgument data: ClientCreateInput): ClientModel {
        return clientService.insert(databaseId, data)
    }
}