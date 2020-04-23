package com.theoxao.service.read

import com.theoxao.common.web.RestResponse
import com.theoxao.repository.UserBookRepository
import com.theoxao.views.UserBookView
import io.ktor.request.ApplicationRequest

/**
 * @author theo
 * @date 19-11-1
 */
class ShelfService(private val userBookRepository: UserBookRepository) {

    suspend fun list(userId:String): RestResponse {
        val list = userBookRepository.list(userId)
        val result = list.map { UserBookView.fromEntity(it) }
        return RestResponse(result)
    }

}
