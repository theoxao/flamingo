package com.theoxao.model

import org.bson.types.ObjectId

import java.util.Date

/**
 * Created by theo on 2018/11/13
 */
class Excerpt {
    var id: ObjectId? = null
    var userId: String? = null
    var refBook: String? = null
    var refPage: Int? = null
    var content: String = ""
    var images: MutableList<String> = arrayListOf()
    var createAt: Date = Date()
    var updateAt: Date = Date()
	var quoto :String = ""
}
