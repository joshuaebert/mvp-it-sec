package de.joshuaebert.mvp.routes

import de.joshuaebert.mvp.config
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

const val API_TEST_ROUTE = "/api/v1/test"

fun Application.configureRouting() {
    routing {
        staticFiles("/", File(config.location))

        get(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK, "Hallo, IT-Sicherheit (WS 24/25)")
        }
        post(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK)
        }
        put(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK)
        }
        delete(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK)
        }
        patch(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK)
        }
        options(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK)
        }
        head(API_TEST_ROUTE) {
            call.respond(HttpStatusCode.OK)
        }
    }
}