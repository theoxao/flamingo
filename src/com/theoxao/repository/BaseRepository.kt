package com.theoxao.repository

import com.theoxao.config.MongoApplication
import org.litote.kmongo.coroutine.CoroutineCollection

/**
 * @author theo
 * @date 20-3-9
 */
abstract class BaseRepository<T>(val mongoApplication: MongoApplication) {

    abstract var collection: String

    inline fun <reified T : Any> getCollection(): CoroutineCollection<T> {
        return this.mongoApplication.database.getCollection(collection)
    }

}
