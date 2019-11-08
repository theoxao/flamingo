package com.theoxao.model

import org.bson.types.ObjectId

import java.util.Date

class UserTag {
    var id: ObjectId? = null
    var userId: String? = null
    var tag: String? = null
    var order: Int? = null
    var creatAt: Date = Date()

    constructor()

    constructor(userId: String?, tag: String?) {
        this.userId = userId
        this.tag = tag
    }

}
