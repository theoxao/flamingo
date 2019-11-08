package com.theoxao.model

import org.bson.types.ObjectId
import java.util.*

class ReadOperation {
    var id: ObjectId? = null
    var userId: String? = null
    var refBookId: String? = null
    var type: Int? = null
    var operation: Int? = null
    var currentPage: Int? = null
    var duration: Long? = null
    var createAt = Date()
}
