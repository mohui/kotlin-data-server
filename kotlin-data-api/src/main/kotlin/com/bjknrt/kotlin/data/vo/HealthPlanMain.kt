package com.bjknrt.kotlin.data.vo

import java.util.Objects
import com.bjknrt.kotlin.data.vo.HealthPlanType
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
 * HealthPlan
 * @param id  主键id
 * @param name  健康计划名称
 * @param type  
 * @param subName  副名称,如:有氧运动-游泳,的游泳
 * @param desc  打卡说明
 * @param time Time 时间
 * @param externalKey  扩展参数（目前只有饮食计划使用）
 * @param cycleStartTime  
 * @param cycleEndTime  
 */
data class HealthPlanMain(
    
    @field:Valid
    @field:JsonProperty("id", required = true) val id: java.math.BigInteger,
    
    @field:JsonProperty("name", required = true) val name: kotlin.String,
    
    @field:Valid
    @field:JsonProperty("type", required = true) val type: HealthPlanType,

    @field:JsonProperty("subName") val subName: kotlin.String? = null,

    @field:JsonProperty("desc") val desc: kotlin.String? = null,

    @field:Valid
    @field:JsonProperty("time") val time: java.time.LocalTime? = null,

    @field:JsonProperty("externalKey") val externalKey: kotlin.String? = null,

    @field:JsonProperty("cycleStartTime") val cycleStartTime: java.time.LocalDateTime? = null,

    @field:JsonProperty("cycleEndTime") val cycleEndTime: java.time.LocalDateTime? = null
) {

}

