package com.qts.erp.fox.datafetchers.global

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.models.global.FieldDefinitionModel
import com.qts.erp.fox.services.global.FieldDefinitionService
import org.springframework.web.bind.annotation.RequestHeader

@DgsComponent
class FieldDefinitionsDataFetcher (private val fieldDefinitionService: FieldDefinitionService) {

    @DgsQuery
    fun fieldsDefinitions(@RequestHeader databaseId: String, @InputArgument tables:List<String>): List<FieldDefinitionModel> {
        return fieldDefinitionService.getFieldsDefinitionsByTableNames(databaseId, tables)
    }
}