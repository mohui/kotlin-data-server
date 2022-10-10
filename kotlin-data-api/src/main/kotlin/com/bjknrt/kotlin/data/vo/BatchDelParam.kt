package com.bjknrt.kotlin.data.vo

import java.util.Objects
import com.bjknrt.kotlin.data.vo.HealthPlanType
import com.bjknrt.framework.api.vo.Id
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
 * batchDelParam
 * @param patientId  
 * @param type  
 */
data class BatchDelParam(
    
    @field:Valid
    @field:JsonProperty("patientId", required = true) val patientId: Id,
    
    @field:Valid
    @field:JsonProperty("type", required = true) val type: HealthPlanType
) {

}

