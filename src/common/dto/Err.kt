package com.theoxao.common.dto

import common.web.RestResponse


/**
 * 错误信息
 * Created by hulingwei on 2017/3/16
 */
class Err {
    val msg: String
    val code: Int

    constructor(msg: String) {
        this.msg = msg
        this.code = RestResponse.SC_INTERNAL_SERVER_ERROR
    }

    constructor(msg: String, code: Int) {
        this.msg = msg
        this.code = code
    }
}
