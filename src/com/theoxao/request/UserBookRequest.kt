package com.theoxao.request

import kotlinx.serialization.Serializable

/**
 * @author theo
 * @date 20-4-8
 */
@Serializable
class UserBookRequest {
    var refBookId: String? = null
    lateinit var userId: String
    var isbn: String? = ""
    lateinit var name: String
    var cover: String? = null
    var author: String? = null
    var pageCount: Int = 0
    var remark: String? = null
    var state: Int = 0
    var recentPage: Int = 0
    var initPage: Int = 0
}
