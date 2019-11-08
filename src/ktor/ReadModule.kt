package com.theoxao.ktor

import com.theoxao.handleRequest
import com.theoxao.service.read.*
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
    val readService: ReadService by inject()
    val bookService: BookService by inject()
    val excerptService: ExcerptService by inject()
    val musicService: MusicService by inject()
    val shelfService: ShelfService by inject()
    val tagService: TagService by inject()

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

        route("/music") {
            get("/list") {
                handleRequest(musicService::list)
            }
        }

        route("/read") {
            post("/add_book") {
                handleRequest(readService::addBook)
            }
            get("/detail") {
                handleRequest(readService::detail)
            }
            get("/read_log") {
                handleRequest(readService::readLog)
            }
            get("/read_stat") {
                handleRequest(readService::readStat)
            }
            post("/read_operate") {
                handleRequest(readService::readOperation)
            }
        }
        route("/shelf") {
            get("/list") {
                handleRequest(shelfService::list)
            }
        }

        route("/tag") {
            get("list") {
                handleRequest(tagService::list)
            }
            post("/add") {
                handleRequest(tagService::add)
            }
            post("/remove") {
                handleRequest(tagService::remove)
            }
            get("/recommend") {
                handleRequest(tagService::recommend)
            }
        }

    }
}
