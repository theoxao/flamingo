package com.theoxao.dto

import com.theoxao.model.ReadRecord
import org.apache.commons.beanutils.BeanUtils
import java.util.*

class ReadRecordView {
    var id: String? = null
    var refBook: String? = null
    var startAt: Date? = null
    var endAt: Date? = null
    var status: Int = 0
    var duration: Long = 0
    var currentPage: Int = 0

    companion object {
        fun fromEntity(entity: ReadRecord): ReadRecordView {
            val record = ReadRecordView()
            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            return record
        }
    }


}
