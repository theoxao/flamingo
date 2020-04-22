package com.theoxao.ktor

import com.theoxao.handleRequest
import com.theoxao.service.read.BookService
import com.theoxao.service.read.ExcerptService
import com.theoxao.service.read.ReadService
import com.theoxao.service.read.ShelfService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

/**
 * @author theo
 * @date 19-11-1
 */
fun Application.read() = with(this) {
    val readService by inject<ReadService>()
    val bookService by inject<BookService>()
    val excerptService by inject<ExcerptService>()
    val shelfService by inject<ShelfService>()

    routing {

        route("book") {
            get("/isbn/{id}") {
                with(call) {
                    respond(bookService.findByIsbn(request))
                }
            }
            get("{id}") {
                with(call) {
                    respond(bookService.findById(request))
                }
            }
            get("/search") {
                with(call) {
                    respond(bookService.search(request))
                }
            }
        }

        route("excerpt") {
            get("/index") {
                handleRequest(excerptService::selectByPage)
            }
            get("/detail") {
                handleRequest(excerptService::findById)
            }
            post("/add") {
                handleRequest(excerptService::add)
            }
            post("/edit") {
                handleRequest(excerptService::edit)
            }
            get("/edit") {
                handleRequest(excerptService::getEdit)
            }
            post("/remove") {
                handleRequest(excerptService::remove)
            }

        }

        route("/read") {
            get("/user_book"){
                handleRequest (readService::getUserBook)
            }

            post("/add_book") {
                handleRequest(readService::addBook)
            }
            get("/read_log") {
                handleRequest(readService::readLog)
            }
            get("/read_stat") {
                handleRequest(readService::readStat)
            }
            post("/add_read_record") {
                handleRequest(readService::readLog)
            }
        }

        route("/shelf") {
            get("/list") {
                handleRequest(shelfService::list)
            }
        }
    }
}
