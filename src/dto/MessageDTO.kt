package com.theoxao.dto

import com.theoxao.model.Message

class MessageDTO {

    var id: String? = null
    var sender: String? = null
    var nickName: String? = null
    var avatarUrl: String? = null
    var type: Int = 0
    var content: Map<String, Any?> = emptyMap()
    var read: Boolean = false

    companion object {

        fun fromEntity(entity: Message): MessageDTO {
            val record = MessageDTO()
//            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            TODO()
            return record
        }
    }
}
