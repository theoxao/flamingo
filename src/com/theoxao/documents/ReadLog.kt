package com.theoxao.model

import org.bson.types.ObjectId
import java.util.*

data class ReadLog(
    var id: ObjectId = ObjectId(),
    var userId: String,
    var refBook: String,
    var startAt: Date,
    var endAt: Date ,
    var duration: Long = 0,
    var currentPage: Int = 0,
    var createAt: Date = Date(),
    var finished: Boolean = false,
    var previousId: ObjectId? = null,
    var kindId: ObjectId
)
