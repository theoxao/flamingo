package com.theoxao.documents

import org.bson.types.ObjectId
import java.util.*

data class UserBook(
    var _id: ObjectId,
    var refBookId: String?,
    var userId: String,
    var isbn: String?,
    var name: String,
    var cover: String?,
    var author: String?,
    var publisher: String?,
    var pageCount: Int,
    var returnDate: Date?,
    var returned: Boolean,
    var remark: String?,
    var state: Int,
    var recentPage: Int,
    var initPage: Int?,
    var createAt: Date?,
    var updateAt: Date?
    )
