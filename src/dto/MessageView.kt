package com.theoxao.dto

import com.theoxao.model.Message

class MessageView {

    var id: String? = null
    var sender: String? = null
    var nickName: String? = null
    var avatarUrl: String? = null
    var type: Int = 0
    var content: Map<String, Any?> = emptyMap()
    var read: Boolean = false

    companion object {

        fun fromEntity(entity: Message): MessageView {
            val record = MessageView()
//            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            TODO()
            return record
        }
    }
}
