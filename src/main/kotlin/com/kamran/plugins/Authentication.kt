package com.kamran.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.util.*

fun Application.configAuth() {

    val digestFunction = getDigestFunction("SHA-256") { "ktor${it.length}" }
    val hashedUserTable = UserHashedTableAuth(
        table = mapOf(
            "jetbrains" to digestFunction("foobar"),
            "admin" to digestFunction("password")
        ),
        digester = digestFunction
    )

    install(Authentication){
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                hashedUserTable.authenticate(credentials)

               /* if (credentials.name == "jetbrains" && credentials.password == "foobar") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }*/
            }
        }
    }
}