package com.theoxao.ktor

import com.theoxao.service.*
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
 * @date 19-10-30
 */

fun Application.user() = with(this) {

    val accountService: AccountService by inject()
    val messageService: MessageService by inject()
    val authService: AuthService by inject()
    val groupService: GroupService by inject()
    val postService: PostService by inject()

    routing {
        route("/account") {
            get("/profile") {
                call.respond(accountService.getProfile(call.request))
            }
        }

        route("/message") {
            get("/list") {
                call.respond(messageService.listMessage(call.request))
            }
        }

        route("auth") {
            post("/login") {
                call.respond(authService.login(call.request))
            }
        }

        route("/group") {
            post("/create") {
                with(call) {
                    respond(groupService.create(request))
                }
            }
            get("/list") {
                with(call) {
                    respond(groupService.list(request))
                }
            }
            get("/detail/{id}") {
                call.respond(groupService.detail(call.request))
            }
            post("/edit") {
                call.respond(groupService.edit(call.request))
            }
            get("/members") {
                call.respond(groupService.members(call.request))
            }
            post("/update_group_name") {
                call.respond(groupService.updateGroupName(call.request))
            }
            post("/quit") {
                call.respond(groupService.quit(call.request))
            }
            post("/join") {
                call.respond(groupService.join(call.request))
            }
            get("/activity") {
                with(call) {
                    respond(groupService.activity(request))
                }
            }
        }

        route("/post") {
            get("/list") {
                with(call) {
                    respond(postService.list(request))
                }
            }
            post("/post") {
                with(call) {
                    respond(postService.post(request))
                }
            }
            post("/remove") {
                with(call) {
                    respond(postService.remove(request))
                }
            }
            post("/like_operation") {
                with(call) {
                    respond(postService.operateLike(request))
                }
            }
            post("/comment") {
                with(call) {
                    respond(postService.comment(request))
                }
            }
        }

    }

}

