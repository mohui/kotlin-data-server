package com.bjknrt.kotlin.data.vo

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

/**
 * UpdStatusParams
 * @param id 主键ID 用药提醒主键
 * @param status 是否可用 true:可用, false: 不可用
 */
data class UpdStatusParams(
    
    @field:Valid
    @field:JsonProperty("id", required = true) val id: java.math.BigInteger,
    
    @field:JsonProperty("status", required = true) val status: kotlin.Boolean
) {

}

