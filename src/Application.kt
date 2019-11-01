package com.theoxao

import com.fasterxml.jackson.databind.SerializationFeature
import com.theoxao.service.*
import com.theoxao.service.read.*
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.base() = with(this) {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Koin) {
        modules(module {

            single { AccountService(get()) }
            single { MessageService(get()) }
            single { AuthService(get()) }
            single { GroupService(get()) }
            single { PostService(get()) }
            single { OCRService(get()) }
            single { BookService(get()) }
            single { ExcerptService(get()) }
            single { MusicService(get()) }
            single { ReadService(get()) }
            single { ShelfService(get()) }
            single { StatService(get()) }
            single { TagService(get()) }


        })
    }
}

