package com.h_fahmy.templates.plugins

import com.h_fahmy.templates.readTemplates
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Application is running")
        }

        get("/templates") {
            call.respondText {
                readTemplates()
            }
        }
    }
}
