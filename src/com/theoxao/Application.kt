package com.theoxao.com.theoxao

import com.theoxao.config.Mongo
import com.theoxao.repository.UserBookRepository
import com.theoxao.service.AuthService
import com.theoxao.service.OCRService
import com.theoxao.service.read.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DoubleReceive
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.request.ApplicationRequest
import io.ktor.request.receiveOrNull
import io.ktor.response.ApplicationResponse
import io.ktor.response.respond
import io.ktor.serialization.json
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.pipeline.PipelineContext
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.jvmErasure


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
        json()
    }

    install(DoubleReceive)

    install(Koin) {
//        fileProperties()
        modules(
            module {
                single { mongo.mongoApplication.database }
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

suspend fun PipelineContext<Unit, ApplicationCall>.handleRequest(function: KFunction<Any>) {
    if (function.isSuspend) {
        val args = arrayListOf(*function.map(this.call.request).toTypedArray())
        val result = suspendCoroutineUninterceptedOrReturn<Any> {
            args.add(it)
            function.call(*args.toTypedArray())
        }
        this.call.respond(result)
    } else {
        val args = function.map(this.call.request).toTypedArray()
        val result = function.call(*args)
        this.call.respond(result)
    }
}

suspend fun <T : Any> KFunction<T>.map(request: ApplicationRequest): List<Any?> {
    return this.parameters.map { param ->
        when (val clazz = param.type.jvmErasure.java) {
            ApplicationRequest::class.java -> request
            ApplicationResponse::class.java -> request.call.response
            java.lang.Integer::class.java, java.lang.Float::class.java,
            java.lang.Double::class.java, java.lang.Long::class.java,
            java.lang.Boolean::class.java, java.lang.String::class.java,
            BigDecimal::class.java, BigInteger::class.java,
            Int::class.java, Float::class.java,
            Double::class.java, Long::class.java,
            Boolean::class.java, String::class.java,
            Char::class.java -> request.call.receiveOrNull(param.type) ?: request.queryParameters[param.name!!]
            else -> request.call.receiveOrNull(param.type) ?: clazz.newInstance().apply {
                clazz.declaredFields.forEach {
                    it.isAccessible = true
                    val value = request.queryParameters[it.name]
                    if (value != null) {
                        it.set(this, value)
                    }
                }
            }

        }
    }
}
