package com.theoxao.common.enums

/**
 * Created by theo on 2018/11/7
 */
enum class BookReadState private constructor(private val code: Int?, private val text: String) {
    not_read(0, "未读"),
    reading(1, "正在阅读"),
    read(2, "已读")
}
