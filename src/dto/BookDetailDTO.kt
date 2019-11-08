package com.theoxao.dto

import com.theoxao.model.UserBook
import org.apache.commons.beanutils.BeanUtils

/**
 * Created by theo on 2018/11/14
 */
class BookDetailDTO : UserBookDTO() {
    var refBook: BookDTO? = null
    var recentRecord: ReadRecordDTO? = null

    companion object {
        fun fromEntity(entity: UserBook): BookDetailDTO {
            val record = BookDetailDTO()
            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            return record
        }
    }
}
