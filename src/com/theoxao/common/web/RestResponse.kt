package com.theoxao.common.web

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class RestResponse(
    var status: Int = 200,
    var error: String? = null,
    var buildTime: String? = System.getProperty("FLAMINGO_BUILD_TIME"),
    @ContextualSerialization var data: Any? = null
) {

    constructor(data: Any) : this() {
        this.status = 200
        this.data = data
    }

    fun ok(): RestResponse {
        this.status = 200
        return this
    }

}
