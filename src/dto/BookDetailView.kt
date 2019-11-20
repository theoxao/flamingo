package com.theoxao.dto

import com.theoxao.model.UserBook
import org.apache.commons.beanutils.BeanUtils

/**
 * Created by theo on 2018/11/14
 */
class BookDetailView : UserBookView() {
    var refBook: BookView? = null
    var recentRecord: ReadRecordView? = null

    companion object {
        fun fromEntity(entity: UserBook): BookDetailView {
            val record = BookDetailView()
            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            return record
        }
    }
}
