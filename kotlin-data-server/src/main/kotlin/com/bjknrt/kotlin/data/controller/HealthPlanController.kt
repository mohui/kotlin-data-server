package com.bjknrt.kotlin.data.controller

import com.bjknrt.kotlin.data.api.HealthPlanApi
import com.bjknrt.framework.api.AppBaseController
import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.*
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController("com.bjknrt.kotlin.data.api.HealthPlanController")
class HealthPlanController() : AppBaseController(), HealthPlanApi {
    override fun batchIdClockIn(id: List<Id>): List<HealthPlan> {

        TODO("Not yet implemented")
    }

    override fun batchIdTypeClockIn(batchTypeClockInParams: BatchTypeClockInParams): List<HealthPlan> {
        TODO("Not yet implemented")
    }

    override fun clockIn(body: BigInteger): List<HealthPlan> {
        TODO("Not yet implemented")
    }

    override fun frequencyGetClockIn(frequencyGetClockInParam: FrequencyGetClockInParam): Int {
        return 1;
    }

    override fun healthPlanFrequencyGetClockIn(healthPlanFrequencyGetClockInParam: HealthPlanFrequencyGetClockInParam): Int {
        TODO("Not yet implemented")
    }

    override fun idGetList(id: List<Id>): List<HealthPlanMain> {
        TODO("Not yet implemented")
    }

    override fun list(): List<HealthPlan> {
        TODO("Not yet implemented")
    }

    override fun patientIdGetList(patientIdGetListParam: PatientIdGetListParam): List<HealthPlanMain> {
        TODO("Not yet implemented")
    }

    override fun upsertFrequencyHealth(frequencyHealthParams: FrequencyHealthParams) {

    }

    override fun upsertTypeFrequencyHealth(frequencyHealthAllParam: FrequencyHealthAllParam): List<UpsertHealthFrequencyResult> {
        TODO("Not yet implemented")
    }

}