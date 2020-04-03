package com.theoxao.service.read

import com.theoxao.common.web.BaseView
import com.theoxao.repository.UserBookRepository
import com.theoxao.views.UserBookView
import common.web.RestResponse
import io.ktor.request.ApplicationRequest

/**
 * @author theo
 * @date 19-11-1
 */
class ReadService(private val userBookRepository: UserBookRepository) {

    suspend fun getUserBook(request: ApplicationRequest): RestResponse<*> {
        val id = request.call.parameters["id"]!!
        val book = userBookRepository.getUserBookById(id)
        return book?.let { RestResponse(UserBookView.fromEntity(it)) }?:RestResponse(BaseView())
    }

    fun addBook(request: ApplicationRequest): RestResponse<*> = TODO()
    fun detail(request: ApplicationRequest): RestResponse<*> = TODO()
    fun readLog(request: ApplicationRequest): RestResponse<*> = TODO()
    fun readStat(request: ApplicationRequest): RestResponse<*> = TODO()
    fun readOperation(request: ApplicationRequest): RestResponse<*> = TODO()

}
