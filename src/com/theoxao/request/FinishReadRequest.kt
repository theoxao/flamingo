package com.theoxao.request

import kotlinx.serialization.Serializable

/**
 * @author theo
 * @date 20-4-10
 */
@Serializable
class FinishReadRequest {

    lateinit var userId: String

    lateinit var startAt: String

    lateinit var endAt: String

    var duration: Long = 0

}
