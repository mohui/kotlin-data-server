package com.bjknrt.kotlin.data.service

import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.FrequencyHealthParams
import java.math.BigInteger

interface HealthPlanService  {
    fun upsertHealth(frequencyHealthParams: FrequencyHealthParams): Id

    fun clockIn(id: BigInteger)
}