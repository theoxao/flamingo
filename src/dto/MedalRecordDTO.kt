package com.theoxao.dto

import com.theoxao.model.Medal
import com.theoxao.model.UserMedalRecord
import java.util.*


class MedalRecordDTO {
    var id: String? = null
    var medal: Medal? = null
    var createAt = Date()


    companion object {
        fun fromEntity(entity: UserMedalRecord): MedalRecordDTO {
            val record = MedalRecordDTO()
//            BeanUtils.copyProperties(entity, record)
            //TODO fix me
            record.id = entity.id?.toHexString()
            return record
        }
    }
}
