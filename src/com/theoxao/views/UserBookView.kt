package com.theoxao.views

import com.theoxao.documents.UserBook
import kotlinx.serialization.Serializable

/**
 * Created by theo on 2018/12/3
 */
@Serializable
data class UserBookView (
    var id: String? = null,
    var refBookId: String?,
    var userId: String?,
    var name: String?,
    var isbn: String?,
    var cover: String?,
    var author: String?,
    var pageCount: Int = 0,
    var remark: String?,
    var recentPage: Int = 0,

    /**
     * tag name list
     */
    var tag: String? = null,

    /**
     * 阅读状态 1 正在阅读 2 已读  3 未读 0 不读
     */
    var state: Int = 0,

    /**
     * 添加时间
     */
    var createAt: Long?,
    var updateAt: Long?
) {
    companion object {
        fun fromEntity(entity: UserBook) = UserBookView(
            id = entity._id.toHexString(),
            userId = entity.userId,
            name = entity.name,
            isbn = entity.isbn,
            cover = entity.cover,
            author = entity.author,
            refBookId = entity.refBookId,
            pageCount = entity.pageCount,
            remark = entity.remark,
            recentPage = entity.recentPage,
            createAt = entity.createAt?.time,
            updateAt = entity.updateAt?.time,
            state = entity.state
        )
    }
}
