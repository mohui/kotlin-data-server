package com.bjknrt.kotlin.data.controller

import com.bjknrt.kotlin.data.api.HealthPlanApi
import com.bjknrt.framework.api.AppBaseController
import com.bjknrt.framework.api.exception.MsgException
import com.bjknrt.framework.api.vo.Id
import com.bjknrt.framework.util.AppIdUtil
import com.bjknrt.framework.util.AppSpringUtil
import com.bjknrt.kotlin.data.service.HealthPlanService
import com.bjknrt.kotlin.data.vo.*
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController("com.bjknrt.kotlin.data.api.HealthPlanController")
class HealthPlanController(
    val healthPlanService: HealthPlanService
) : AppBaseController(), HealthPlanApi {
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
        // 添加主表
        val id = healthPlanService.upsertHealth(frequencyHealthParams)
            ?: throw MsgException(AppSpringUtil.getMessage("mrs.add-health-plan-fail"))
        val frequency = frequencyHealthParams.frequencys

        if (frequency != null) {
            for (it in frequency) {
                val addFrequency = mutableListOf<FrequencyParams>()
                // 第一层
                addFrequency.add(
                    FrequencyParams(
                        id = AppIdUtil.nextId(),
                        healthPlanId = id,
                        explainId = null,
                        frequencyTime = it.frequencyTime,
                        frequencyTimeUnit = it.frequencyTimeUnit,
                        frequencyNum = it.frequencyNum,
                        frequencyNumUnit = it.frequencyNumUnit
                    )
                )
                // 下级
                var child = it.children

                // 如果下级不是null, 说明下级还有下级
                while (child != null) {
                    // 把下级放到数组中
                    addFrequency.add(
                        FrequencyParams(
                            id = AppIdUtil.nextId(),
                            healthPlanId = id,
                            explainId = addFrequency.last().id,
                            frequencyTime = child.frequencyTime,
                            frequencyTimeUnit = child.frequencyTimeUnit,
                            frequencyNum = child.frequencyNum,
                            frequencyNumUnit = child.frequencyNumUnit
                        )
                    )
                    child = child.children
                }

                healthPlanService.upsertFrequency(addFrequency)
            }
        }
    }

    override fun upsertTypeFrequencyHealth(frequencyHealthAllParam: FrequencyHealthAllParam): List<UpsertHealthFrequencyResult> {
        TODO("Not yet implemented")
    }

}