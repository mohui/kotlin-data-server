package com.bjknrt.kotlin.data.service

import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.FrequencyHealthParams
import com.bjknrt.kotlin.data.vo.FrequencyParams
import java.math.BigInteger

interface HealthPlanService  {
    fun upsertHealth(frequencyHealthParams: FrequencyHealthParams): Id

    // 健康计划规则添加修改
    fun upsertFrequency(frequency: List<FrequencyParams>)

    fun clockIn(id: BigInteger)
}