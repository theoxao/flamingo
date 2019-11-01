package com.theoxao.ktor

import com.theoxao.service.read.BookService
import com.theoxao.service.read.ExcerptService
import com.theoxao.service.read.ReadService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

/**
 * @author theo
 * @date 19-11-1
 */
fun Application.read() = with(this){
    val readService:ReadService by inject()
    val bookService:BookService by inject()
    val excerptService:ExcerptService by inject()

    routing {

        route("book"){
            get("/isbn/{id}"){
                with(call){
                    respond(bookService.findByIsbn(request))
                }
            }
            get("{id}"){
                with(call){
                    respond(bookService.findById(request))
                }
            }
            get("/search"){
                with(call){
                    respond(bookService.search(request))
                }
            }
        }

        route("excerpt"){

        }


    }
}
