package com.theoxao.service.read

import com.theoxao.common.web.RestResponse
import com.theoxao.documents.UserBook
import com.theoxao.model.ReadLog
import com.theoxao.repository.ReadLogRepository
import com.theoxao.repository.UserBookRepository
import com.theoxao.request.ReadLogRequest
import com.theoxao.request.UserBookRequest
import com.theoxao.views.UserBookView
import io.ktor.request.ApplicationRequest
import org.bson.types.ObjectId
import java.util.*

/**
 * @author theo
 * @date 19-11-1
 */
class ReadService(
    private val userBookRepository: UserBookRepository,
    private val readLogRepository: ReadLogRepository
) {

    suspend fun getUserBook(id: String): RestResponse {
        val book = userBookRepository.getUserBookById(id)
        return book?.let { RestResponse(UserBookView.fromEntity(it)) } ?: RestResponse()
    }

    suspend fun addBook(request: UserBookRequest): RestResponse {
        val userBook = UserBook(
            ObjectId(),
            null,
            request.userId,
            request.isbn,
            request.name,
            request.cover,
            request.author,
            request.pageCount,
            request.remark,
            0,
            request.recentPage,
            request.initPage,
            Date(),
            Date()
        )
        userBookRepository.save(userBook)
        return RestResponse()
    }

    fun readStat(request: ApplicationRequest): RestResponse = TODO()

    suspend fun readLog(request: ReadLogRequest) {
        val lastRecord = readLogRepository.getPreviousLog(request.userId,request.refBookId)
        val readLog = ReadLog(
            ObjectId(),
            request.userId,
            request.refBookId,
            Date(request.startAt),
            Date(request.endAt),
            request.duration,
            request.currentPage,
            Date(),
            false , //TODO
            lastRecord?.previousId,
            lastRecord?.kindId?:ObjectId()
        )
        readLogRepository.saveLog(readLog)
    }

}
