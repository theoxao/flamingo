package com.theoxao.repository

import com.theoxao.config.MongoApplication
import com.theoxao.model.ReadLog
import org.litote.kmongo.descending
import org.litote.kmongo.eq

/**
 * @author theo
 * @date 2020/4/21
 */
class ReadLogRepository(
    mongoApplication: MongoApplication,
    override var collection: String = "read_log"
) : BaseRepository<ReadLog>(mongoApplication) {

    suspend fun getPreviousLog(userId: String, refBookId: String): ReadLog? {
        return getCollection<ReadLog>().find(ReadLog::userId eq userId, ReadLog::refBook eq refBookId)
            .toList().maxBy { it.createAt }
    }

    suspend fun saveLog(readLog: ReadLog) {
        getCollection<ReadLog>().save(readLog)
    }

}
