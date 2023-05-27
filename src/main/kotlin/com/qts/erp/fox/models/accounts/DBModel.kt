package com.qts.erp.fox.models.accounts

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

class DBModel(
    var name: String,
    var description: String,
    @get:JsonProperty("db_key")
    var key: String,
    @get:JsonProperty("companyLogoUrl")
    var companyLogoUrl: String,
    @get:JsonProperty("lastBackUp")
    var lastBackUp: OffsetDateTime?,
    // Este vendedor toma valor dependiendo si el usuario que se pasa como parametro en el login,
    // tiene vendedor creado en esa empresa o no, por ende, seller puede ser null y por dicha causa no se debe acceder
    // a objetos internos del SellerModel como por ejemplo el entity o cualquier otro, ya que GraphQL lanzara una excepcion
    // El seller solo se agrega aqui, para saber si una empresa tiene un venddor creado para el usuario, asi que con
    // solo el id del seller, puedo saber si lo tiene o no
)
{
    var serverId: Int = 0
    constructor(): this(
        name = "",
        description = "",
        key = "",
        companyLogoUrl = "",
        lastBackUp = null
    )
}