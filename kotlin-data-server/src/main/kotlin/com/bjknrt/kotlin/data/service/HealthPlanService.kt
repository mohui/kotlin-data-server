package com.bjknrt.kotlin.data.service

import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.CalculationCycleResult
import com.bjknrt.kotlin.data.vo.FrequencyHealthParams
import com.bjknrt.kotlin.data.vo.FrequencyParams
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

interface HealthPlanService  {
    fun upsertHealth(frequencyHealthParams: FrequencyHealthParams): Id

    // 健康计划规则添加修改
    fun upsertFrequency(frequency: List<FrequencyParams>)

    fun clockIn(id: BigInteger)

    /**
     * 时间周期
     */
    fun calculationCycle(
        chronoNum: Int,
        chronoUnit: ChronoUnit,
        startDateTime: LocalDateTime,
        now: LocalDateTime = LocalDateTime.now()
    ): CalculationCycleResult
}