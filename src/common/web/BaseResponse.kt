package com.theoxao.common.web

import kotlinx.serialization.Serializable

/**
 * @author theo
 * @date 20-3-13
 */
@Serializable
open  class BaseResponse(var responseCode: Int =200, var responseMessage: String? =null){

    companion object{
        fun notFount(): BaseResponse {
            return BaseResponse(404, "record not fount")
        }
    }

}
