package com.theoxao.request

import kotlinx.serialization.Serializable

/**
 * @author theo
 * @date 20-4-10
 */
@Serializable
class ReadLogRequest {

    lateinit var userId: String

     lateinit var refBookId:String

    var startAt: Long =0

    var endAt: Long = 0

    var duration: Long = 0

    var currentPage :Int = 0

}
