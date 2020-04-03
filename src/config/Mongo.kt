package com.theoxao.config

import io.ktor.application.Application
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.ApplicationFeature
import io.ktor.util.AttributeKey
import io.ktor.util.KtorExperimentalAPI
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

/**
 * @author theo
 * @date 20-3-4
 */


class Mongo(val mongoApplication: MongoApplication) {

    companion object Feature : ApplicationFeature<Application, MongoApplication, Mongo> {
        override fun install(
            pipeline: Application,
            configure: MongoApplication.() -> Unit
        ): Mongo {
            val application = MongoApplication().apply(configure)
            return Mongo(application)
        }
        override val key: AttributeKey<Mongo> = AttributeKey("Kmongo")
    }
}

class MongoApplication {

    lateinit var database: CoroutineDatabase

    inline fun <reified T : Any> getCollection(name: String) = database.getCollection<T>(name)

    @KtorExperimentalAPI
    fun Application.kmongo(property: String = "database.mongo.uri") {
        database =
            KMongo.createClient(this.environment.config.property(property).getString()).coroutine.getDatabase("test")
    }
}

