package com.theoxao.model

import org.bson.types.ObjectId
import java.util.*

class UserMedalRecord {

    var id: ObjectId? = null
    var medalId: String? = null
    var medal: Medal? = null
    var userId: String? = null
    var createAt = Date()

}
