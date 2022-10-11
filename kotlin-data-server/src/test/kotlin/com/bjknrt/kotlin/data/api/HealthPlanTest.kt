package com.bjknrt.kotlin.data.api

import com.bjknrt.kotlin.data.vo.FrequencyGetClockInParam
import com.bjknrt.kotlin.data.vo.FrequencyHealthAllParam
import com.bjknrt.kotlin.data.vo.HealthPlan
import com.bjknrt.kotlin.data.vo.HealthPlanFrequencyGetClockInParam
import com.bjknrt.kotlin.data.vo.HealthPlanMain
import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.PatientIdGetListParam
import com.bjknrt.kotlin.data.vo.UpsertHealthFrequencyResult
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import com.bjknrt.kotlin.data.AbstractContainerBaseTest

class HealthPlanTest : AbstractContainerBaseTest() {

    @Autowired
    lateinit var api: HealthPlanApi

    /**
     * To test HealthPlanApi.clockIn
     */
    @Test
    fun clockInTest() {
        val body: java.math.BigInteger = TODO()
        val response = api.clockIn(body)

        // TODO: test validations
    }
}
