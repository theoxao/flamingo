package com.theoxao.repository

import com.theoxao.config.MongoApplication
import com.theoxao.model.ReadLog
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

/**
 * @author theo
 * @date 20-3-9
 */
abstract class BaseRepository<T>(val mongoApplication: MongoApplication) {

    abstract var collection: String

    inline fun <reified T : Any> getCollection(): CoroutineCollection<T> {
        return this.mongoApplication.database.getCollection<T>(collection)
    }

}
