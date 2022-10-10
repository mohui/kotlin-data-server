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
        val response: List<HealthPlan> = api.clockIn(body)

        // TODO: test validations
    }

    /**
     * To test HealthPlanApi.frequencyGetClockIn
     */
    @Test
    fun frequencyGetClockInTest() {
        val frequencyGetClockInParam: FrequencyGetClockInParam = TODO()
        val response: kotlin.Int = api.frequencyGetClockIn(frequencyGetClockInParam)

        // TODO: test validations
    }

    /**
     * To test HealthPlanApi.healthPlanFrequencyGetClockIn
     */
    @Test
    fun healthPlanFrequencyGetClockInTest() {
        val healthPlanFrequencyGetClockInParam: HealthPlanFrequencyGetClockInParam = TODO()
        val response: kotlin.Int = api.healthPlanFrequencyGetClockIn(healthPlanFrequencyGetClockInParam)

        // TODO: test validations
    }

    /**
     * To test HealthPlanApi.idGetList
     */
    @Test
    fun idGetListTest() {
        val id: kotlin.collections.List<Id> = TODO()
        val response: List<HealthPlanMain> = api.idGetList(id)

        // TODO: test validations
    }

    /**
     * To test HealthPlanApi.list
     */
    @Test
    fun listTest() {
        val response: List<HealthPlan> = api.list()

        // TODO: test validations
    }

    /**
     * To test HealthPlanApi.patientIdGetList
     */
    @Test
    fun patientIdGetListTest() {
        val patientIdGetListParam: PatientIdGetListParam = TODO()
        val response: List<HealthPlanMain> = api.patientIdGetList(patientIdGetListParam)

        // TODO: test validations
    }

    /**
     * To test HealthPlanApi.upsertTypeFrequencyHealth
     */
    @Test
    fun upsertTypeFrequencyHealthTest() {
        val frequencyHealthAllParam: FrequencyHealthAllParam = TODO()
        val response: List<UpsertHealthFrequencyResult> = api.upsertTypeFrequencyHealth(frequencyHealthAllParam)

        // TODO: test validations
    }
}
