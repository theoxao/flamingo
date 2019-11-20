package com.theoxao.dto


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.theoxao.model.UserTag
import org.apache.commons.beanutils.BeanUtils
import java.util.*

/**
 * Created by theo on 2018/12/3
 */
@JsonIgnoreProperties("userId")
class UserTagView {
    var id: String? = null
    var userId: String? = null
    var tag: String? = null
    var order: Int? = null
    var createAt: Date = Date()

    constructor()

    constructor(tag: String?) {
        this.tag = tag
    }


    companion object {
        fun fromEntity(entity: UserTag): UserTagView {
            val record = UserTagView()
            BeanUtils.copyProperties(entity, record)
            record.id = entity.id?.toHexString()
            return record
        }
    }
}
