package com.kamran.plugins

import com.kamran.routes.customerRouting
import com.kamran.routes.getOrderRoute
import com.kamran.routes.listOrdersRoute
import com.kamran.routes.totalizeOrderRoute
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        authenticate("auth-basic") {
            get("/") {
                call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}!")
            }
            customerRouting()
            listOrdersRoute()
            getOrderRoute()
            totalizeOrderRoute()
        }
    }
}
