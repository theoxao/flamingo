package com.theoxao.model

import org.bson.types.ObjectId
import java.util.*

class ReadLog {

    var id: ObjectId = ObjectId()
    var userId: String? = null
    var refBook: String? = null
    var startAt: Date? = null
    var endAt: Date? = null
    var duration: Long = 0
    var currentPage: Int? = null
    var createAt: Date = Date()
    var updateAt: Date = Date()
    var finished: Boolean = false
    var previousId: ObjectId? = null
    var kindId: ObjectId? = null
}
