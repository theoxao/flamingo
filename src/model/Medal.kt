package com.theoxao.model

import org.bson.types.ObjectId

class Medal {

    var id: ObjectId? = null
    var name: String? = null
    var desc: String? = null
    var type: Int? = null
    var condition: Int = 0
    var conditionDesc: String? = null

}
