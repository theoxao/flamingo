package com.theoxao.dto

import com.theoxao.model.Medal
import com.theoxao.model.UserMedalRecord
import java.util.*


class MedalRecordView {
    var id: String? = null
    var medal: Medal? = null
    var createAt = Date()


    companion object {
        fun fromEntity(entity: UserMedalRecord): MedalRecordView {
            val record = MedalRecordView()
//            BeanUtils.copyProperties(entity, record)
            //TODO fix me
            record.id = entity.id?.toHexString()
            return record
        }
    }
}
