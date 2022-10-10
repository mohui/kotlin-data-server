package com.bjknrt.kotlin.data.vo

import java.util.Objects
import com.bjknrt.kotlin.data.vo.FrequencyHealthParams
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
 * frequencyHealthAllParam
 * @param healthPlans  健康计划
 * @param type  
 */
data class FrequencyHealthAllParam(
    
    @field:Valid
    @get:Size(min=1)
    @field:JsonProperty("healthPlans", required = true) val healthPlans: kotlin.collections.List<FrequencyHealthParams>,

    @field:Valid
    @field:JsonProperty("type") val type: HealthPlanType? = null
) {

}

