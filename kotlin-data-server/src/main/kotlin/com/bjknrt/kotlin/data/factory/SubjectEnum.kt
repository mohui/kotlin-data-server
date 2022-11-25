package com.bjknrt.kotlin.data.factory

import com.fasterxml.jackson.annotation.JsonProperty

enum class SubjectEnum(val value: kotlin.String) {

    /**
     * 语文
     */
    @JsonProperty("CHINESE") CHINESE("语文"),

    /**
     * 数学
     */
    @JsonProperty("MATHEMATICS") MATHEMATICS("数学"),

    /**
     * 英语
     */
    @JsonProperty("ENGLISH") ENGLISH("英语"),

    /**
     * java编程
     */
    @JsonProperty("JAVA") JAVA("java编程")
}