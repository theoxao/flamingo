package com.theoxao.service.read

import com.theoxao.common.web.BaseResponse
import com.theoxao.repository.UserBookRepository
import com.theoxao.views.UserBookView
import io.ktor.request.ApplicationRequest

/**
 * @author theo
 * @date 19-11-1
 */
class ReadService(private val userBookRepository: UserBookRepository) {

    suspend fun getUserBook(request: ApplicationRequest): BaseResponse {
        val id = request.call.parameters["id"] ?: throw RuntimeException(" parameter missing")
        val book = userBookRepository.getUserBookById(id)
        return book?.let { UserBookView.fromEntity(it) } ?: BaseResponse.notFount()
    }

    fun addBook(request: ApplicationRequest): BaseResponse = TODO()
    fun detail(request: ApplicationRequest): BaseResponse = TODO()
    fun readLog(request: ApplicationRequest): BaseResponse = TODO()
    fun readStat(request: ApplicationRequest): BaseResponse = TODO()
    fun readOperation(request: ApplicationRequest): BaseResponse = TODO()

}
