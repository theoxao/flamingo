package com.theoxao.repository

import com.theoxao.config.MongoApplication
import com.theoxao.documents.UserBook
import org.bson.types.ObjectId
import org.litote.kmongo.eq

/**
 * @author theo
 * @date 20-3-4
 */
class UserBookRepository(mongoApplication: MongoApplication) : BaseRepository<UserBook>(mongoApplication) {

    override var collection: String = "user_book"

    suspend fun getUserBookById(id:String): UserBook? {
        return getCollection<UserBook>().findOne(UserBook::_id eq ObjectId(id))
    }

}
