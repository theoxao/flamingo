package common.web


import com.fasterxml.jackson.annotation.JsonIgnore
import com.theoxao.common.dto.Err
import com.theoxao.dto.MedalRecordDTO
import java.util.*


class RestResponse<T>() {
    private var status = SC_INTERNAL_SERVER_ERROR
    private var error: String? = null
    var timestamp: Long? = Date().time
        set(timestamp) {
            field = this.timestamp
        }
    private var data: T? = null

    val isSuccess: Boolean
        @JsonIgnore
        get() = status == SC_OK

    constructor(data: T) : this() {
        this.status = SC_OK
        this.data = data
    }

    fun ok(): RestResponse<T> {
        this.status = SC_OK
        return this
    }

    fun withStatus(status: Int): RestResponse<T> {
        this.status = status
        return this
    }

    fun withError(error: String): RestResponse<T> {
        this.error = error
        return this
    }

    fun withError(error: Err?): RestResponse<T> {
        if (error == null) {
            return this.ok()
        }
        this.error = error.msg
        this.status = error.code
        return this
    }

    fun withData(data: T): RestResponse<T> {
        this.data = data
        return this
    }

    companion object {
        fun <T> notFound(): RestResponse<T> {
            return RestResponse<T>().withStatus(SC_NOT_FOUND)
        }

        val STATIC_ERROR_RESPONSE = "{\"status\": 500, \"error\": \"服务器内部错误!\", \"data\": null}"
        /**
         * 请求成功
         */
        val SC_OK = 200
        /**
         * 重定向
         */
        val SC_MOVED_TEMPORARILY = 302
        /**
         * 服务器异常
         */
        val SC_INTERNAL_SERVER_ERROR = 500
        /**
         * 资源不存在
         */
        val SC_NOT_FOUND = 404
        /**
         * 没有权限
         */
        val SC_UNAUTHORIZED = 401
        /**
         * 需要付费
         */
        val SC_PAYMENT_REQUIRED = 402


    }
}
