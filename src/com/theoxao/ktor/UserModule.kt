package com.theoxao.ktor

import com.theoxao.service.*
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

/**
 * @author theo
 * @date 19-10-30
 */

fun Application.user() = with(this) {

    val authService by inject<AuthService>()

    routing {

        route("auth") {
            post("/login") {
                call.respond(authService.login(call.request))
            }
        }

    }

}

