package com.bjknrt.kotlin.data.factory

import com.bjknrt.kotlin.data.vo.FrequencyNumUnit
import com.bjknrt.kotlin.data.vo.TimeUnit
import com.fasterxml.jackson.annotation.JsonProperty

data class SubjectResult (
    val id: java.math.BigInteger,

    @field:JsonProperty("name", required = true) val name: kotlin.String,

    val score: Int
) {
}