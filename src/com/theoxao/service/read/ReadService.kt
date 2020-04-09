package com.theoxao.service.read

import com.theoxao.common.web.BaseView
import com.theoxao.common.web.RestResponse
import com.theoxao.documents.UserBook
import com.theoxao.repository.UserBookRepository
import com.theoxao.request.UserBookRequest
import com.theoxao.views.UserBookView
import io.ktor.request.ApplicationRequest
import org.bson.types.ObjectId
import java.util.*

/**
 * @author theo
 * @date 19-11-1
 */
class ReadService(private val userBookRepository: UserBookRepository) {

    suspend fun getUserBook(request: ApplicationRequest): RestResponse {
        val id = request.call.parameters["id"]!!
        val book = userBookRepository.getUserBookById(id)
        return book?.let { RestResponse(UserBookView.fromEntity(it)) } ?: RestResponse(
            BaseView("a")
        )
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


    fun detail(request: ApplicationRequest): RestResponse = TODO()


    fun readLog(request: ApplicationRequest): RestResponse = TODO()
    fun readStat(request: ApplicationRequest): RestResponse = TODO()
    fun readOperation(request: ApplicationRequest): RestResponse = TODO()

}
