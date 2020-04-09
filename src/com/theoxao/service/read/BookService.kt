package com.theoxao.service.read

import com.theoxao.documents.UserBook
import io.ktor.request.ApplicationRequest

/**
 * @author theo
 * @date 19-11-1
 */
class BookService(ph: Any) {
    fun findByIsbn(request: ApplicationRequest): Any {
        val userBook = UserBook::class.java.mapRequest(request)

        return ""
    }

    fun findById(request: ApplicationRequest): Any = TODO()
    fun search(request: ApplicationRequest): Any = TODO()


    inline fun <reified T : Any> Class<T>.mapRequest(request: ApplicationRequest): T {
        val instance = T::class.java.newInstance()
        this.declaredFields.forEach {
            it.isAccessible= true
            it.set( instance , request.call.parameters[it.name])
        }
        return instance
    }
}
