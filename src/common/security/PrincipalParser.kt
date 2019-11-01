package com.theoxao.commons.security

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.HttpHeaders
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import java.util.*

/**
 * Created by Dolphin on 2018/4/3
 */
class PrincipalParser(private val principalHeader: String) {
    private val objectMapper = ObjectMapper()

    init {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        INSTANCE = this
    }

    fun parsePrincipal(headers: io.ktor.http.Headers): Optional<Principal> {
        if (INSTANCE == null) {
            log.error("PrincipalParser not initialized!")
            return Optional.empty()
        }
        val principalStr = headers[INSTANCE!!.principalHeader]
        if (principalStr != null && principalStr.length > 0) {
            try {
                val principal = INSTANCE!!.objectMapper.readValue(
                    String(
                        Base64.getDecoder().decode(principalStr),
                        Charset.forName("UTF-8")
                    ), Principal::class.java
                )
                principal.principalText = principalStr
                return Optional.of(principal)
            } catch (e: Exception) {
                log.error("failed to parse principal:$principalStr", e)
            }

        }
        return Optional.empty()
    }

    companion object {
        private val log = LoggerFactory.getLogger(PrincipalParser::class.java)
        private var INSTANCE: PrincipalParser? = null
    }
}
