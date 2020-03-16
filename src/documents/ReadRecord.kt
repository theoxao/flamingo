package com.theoxao.model

import org.bson.types.ObjectId
import java.util.*

class ReadRecord(var userId: String, var refBook: String?, var startAt: Date?, var endAt: Date?, var kindId: ObjectId?, var currentPage: Int? ,
                 var pageCount: Int) {
    var id: ObjectId? = null
    var duration: Long = 0
}
