package com.theoxao.dto

import com.theoxao.model.Excerpt
import org.apache.commons.beanutils.BeanUtils


import java.util.Date

/**
 * Created by theo on 2018/11/13
 */
class ExcerptView {
    var id: String? = null
    var userId: String? = null
    var refBook: String? = null
    var refPage: Int? = null
    var content: String? = null
    var images: MutableList<String> = arrayListOf()
    var createAt: Date? = null

    companion object {
        fun fromEntity(entity: Excerpt): ExcerptView {
            val record = ExcerptView()
            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            return record
        }
    }
}
