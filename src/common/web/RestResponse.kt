package common.web

import com.theoxao.common.web.BaseView
import kotlinx.serialization.*

@Serializer(forClass = RestResponse::class)
object thisSerializer {}


@Serializable
data class RestResponse<T : BaseView>(
    var status: Int = 500,
    var error: String? = null,
    var data: T? = null
) {


    constructor(data: T) : this() {
        this.status = 200
        this.data = data
    }

    fun ok(): RestResponse<T> {
        this.status = 200
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

    fun withData(data: T): RestResponse<T> {
        this.data = data
        return this
    }

}
