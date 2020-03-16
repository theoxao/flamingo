package com.theoxao

import com.theoxao.common.web.BaseResponse
import com.theoxao.config.Mongo
import com.theoxao.repository.BaseRepository
import com.theoxao.repository.UserBookRepository
import com.theoxao.service.AuthService
import com.theoxao.service.OCRService
import com.theoxao.service.read.*
import common.web.RestResponse
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.request.ApplicationRequest
import io.ktor.response.respond
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.ktor.ext.Koin


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
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

    val mongo = install(Mongo) {
        kmongo()
    }

    install(ContentNegotiation) {
        serialization()
    }

    install(Koin) {
        fileProperties()
        modules(
            module {
                single { mongo.mongoApplication }
                single { UserBookRepository(get()) }
            },
            module {
                single { AuthService(get()) }
                single { OCRService(get()) }
                single { BookService(get()) }
                single { ExcerptService(get()) }
                single { ReadService(get()) }
                single { ShelfService(get()) }
                single { StatService(get()) }
            }
        )

    }
}

suspend inline fun <T> ApplicationCall.handleRequest(function: (request: ApplicationRequest) -> RestResponse<T>) {
    respond(function(this.request))
}

suspend inline fun PipelineContext<Unit, ApplicationCall>.handleRequest(function: (request: ApplicationRequest) -> BaseResponse) {
    this.call.respond(function(this.call.request))
}

suspend inline fun PipelineContext<Unit, ApplicationCall>.handleRequest(  function:  suspend (request: ApplicationRequest) -> BaseResponse) {
    this.call.respond(function(this.call.request))
}
