package com.bjknrt.kotlin.data.controller

import cn.hutool.core.date.DateField
import cn.hutool.core.date.DateTime
import cn.hutool.core.date.DateUtil
import cn.hutool.core.date.LocalDateTimeUtil
import com.bjknrt.kotlin.data.api.HealthPlanApi
import com.bjknrt.framework.api.AppBaseController
import com.bjknrt.framework.api.exception.MsgException
import com.bjknrt.framework.api.vo.Id
import com.bjknrt.framework.util.AppIdUtil
import com.bjknrt.framework.util.AppSpringUtil
import com.bjknrt.kotlin.data.service.HealthPlanService
import com.bjknrt.kotlin.data.vo.*
import me.danwi.sqlex.core.query.now
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import java.time.*
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.*

@RestController("com.bjknrt.kotlin.data.api.HealthPlanController")
class HealthPlanController(
    val healthPlanService: HealthPlanService
) : AppBaseController(), HealthPlanApi {
    override fun batchIdClockIn(id: List<Id>) {
        val dayOfMonth = LocalDate.now().dayOfMonth
        println("这个是dayOfMonth${dayOfMonth}")


        val dayOfYear = LocalDateTime.now().dayOfYear
        println("这个是 dayOfYear${dayOfYear}")


        val weekOfYear = LocalDateTime.now().with(ChronoField.ALIGNED_WEEK_OF_YEAR, 2)
        println("这个是 weekOfYear${weekOfYear}")

        // 获取当前周
        val week = LocalDateTime.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR)
        println("这个是 get方法${week}")

        // 调用方法
//        healthPlanService.getListTest()
//        val time = LocalDateTimeUtil.now()
    }

    override fun batchIdTypeClockIn() {
        val startTime = LocalDateTime.now().toLocalDate().atStartOfDay().plusDays(-10)
        val startTime1 = LocalDate.of(LocalDate.now().year, 1, 1).atStartOfDay()
        val time = healthPlanService.calculationCycle(
            chronoNum = 3,
            chronoUnit = ChronoUnit.MONTHS,
            startDateTime = startTime1,
            now = LocalDateTime.now()
        )
        println(time)
    }

    override fun clockIn(body: BigInteger) {
        healthPlanService.getListTest()
    }

    override fun frequencyGetClockIn(frequencyGetClockInParam: FrequencyGetClockInParam): Int {
        return 1;
    }

    override fun healthPlanFrequencyGetClockIn(healthPlanFrequencyGetClockInParam: HealthPlanFrequencyGetClockInParam): Int {
        TODO("Not yet implemented")
    }

    override fun idGetList(id: List<Id>) {

        val now = DateTime.now()

        //将时间设置为今年的第二周的当前时间
        now.setField(DateField.WEEK_OF_YEAR, 3)

        //获取第二周的开始时间
        val beginOfWeek = DateUtil.beginOfWeek(now)
        println(beginOfWeek)

        //获取第二周的结束时间
        val endOfWeek = DateUtil.endOfWeek(now)
        println(endOfWeek)
    }

    override fun list(): List<HealthPlan> {
        TODO("Not yet implemented")
    }

    override fun patientIdGetList(patientIdGetListParam: PatientIdGetListParam) {
        val now1= org.joda.time.DateTime.now()
        println(now1)
        val time1 = now1.withYear(2022).withWeekOfWeekyear(1).dayOfWeek().withMinimumValue()
        println(time1)
        val weekOfWeekyear = now1.weekOfWeekyear()
        println(weekOfWeekyear.get())
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

    override fun upsertTypeFrequencyHealth(frequencyHealthAllParam: FrequencyHealthAllParam) {

    }

}