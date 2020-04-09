package com.theoxao.views

import com.theoxao.model.ReadLog
import com.theoxao.model.ReadRecord
import org.apache.commons.beanutils.BeanUtils

import java.util.Date

/**
 * Created by theo on 2018/12/3
 */
class ReadLogView {
    var id: String? = null
    var userId: String? = null
    var refBook: String? = null
    var startAt: Date? = null
    var endAt: Date? = null
    var duration: Long? = null
    var createAt: Date? = null
    var currentStatus: Int = 0
    /**
     * 阅读类型 0 普通阅读 1 倒计时阅读
     */
    var type: Int? = null

    companion object {

        fun fromEntity(entity: ReadLog, currentStatus: Int = 0): ReadLogView {
            val record = ReadLogView()
            BeanUtils.copyProperties(entity, record)
            record.id = entity.id.toHexString()
            record.currentStatus = currentStatus
            return record
        }

        fun fromRecord(entity: ReadRecord, currentStatus: Int = 0): ReadLogView {
            val record = ReadLogView()
            BeanUtils.copyProperties(entity, record)
            record.currentStatus = currentStatus
            return record
        }
    }
}
