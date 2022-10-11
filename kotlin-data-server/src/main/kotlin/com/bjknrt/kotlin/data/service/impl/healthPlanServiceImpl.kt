package com.bjknrt.kotlin.data.service.impl

import com.bjknrt.framework.api.vo.Id
import com.bjknrt.framework.util.AppIdUtil
import com.bjknrt.kotlin.data.MrFrequency
import com.bjknrt.kotlin.data.MrFrequencyTable
import com.bjknrt.kotlin.data.MrHealthPlan
import com.bjknrt.kotlin.data.MrHealthPlanTable
import com.bjknrt.kotlin.data.service.HealthPlanService
import com.bjknrt.kotlin.data.vo.*
import com.bjknrt.user.permission.centre.security.AppSecurityUtil
import com.google.gson.Gson
import me.danwi.sqlex.core.query.Order
import me.danwi.sqlex.core.query.arg
import me.danwi.sqlex.core.query.`in`
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Service
class HealthPlanServiceImpl(
    val table: MrHealthPlanTable,
    val mrFrequencyTable: MrFrequencyTable
): HealthPlanService {

    override fun calculationCycle(
        chronoNum: Int,
        chronoUnit: ChronoUnit,
        startDateTime: LocalDateTime,
        now: LocalDateTime
    ): CalculationCycleResult {
        val start: LocalDateTime
        val end: LocalDateTime

        val between: Long = chronoUnit.between(startDateTime, now) / chronoNum
        val temp = chronoUnit.addTo(startDateTime, between * chronoNum)
        if (temp.isAfter(now)) {
            start = chronoUnit.addTo(startDateTime, between * chronoNum - chronoNum)
            end = temp
        } else {
            start = temp
            end = chronoUnit.addTo(startDateTime, between * chronoNum + chronoNum)
        }

        return CalculationCycleResult(
            start = start,
            end = end,
            between = between
        )
    }

    override fun clockIn(id: BigInteger) {
    }

    /**
     * 添加
     */
    @Transactional
    override fun upsertHealth(frequencyHealthParams: FrequencyHealthParams): Id {
        // name: 计划名称, id:主键, 副标题, 运动频次时间, 运动频次时间单位, 运动频次数量, 运动频次数量单位, cycleStartTime: 计划时间
        // 计划名称
        val name = frequencyHealthParams.name;

        // 计划类型
        val type = frequencyHealthParams.type
        // 患者ID
        val patientId = frequencyHealthParams.patientId
        // 主键
        val id = frequencyHealthParams.id ?: AppIdUtil.nextId()
        // 副标题
        val subName = frequencyHealthParams.subName
        // 描述
        val desc = frequencyHealthParams.desc
        // 饮食计划使用
        val externalKey = frequencyHealthParams.externalKey
        // 计划开始时间
        val cycleStartTime = frequencyHealthParams.cycleStartTime
        // 计划结束时间
        val cycleEndTime = frequencyHealthParams.cycleEndTime
        ///频次
        val frequencys = frequencyHealthParams.frequencys

        // 登录人id
        val createdId = AppSecurityUtil.currentUserIdWithDefault();
        // 当前日期时间
        val current = LocalDateTime.now()

        // 添加主表
        table.saveOrUpdate(MrHealthPlan.forInsert(
            id,
            patientId,
            type.value,
        ).apply {
            this.knCreatedAt = current
            this.knCreatedBy = createdId
            this.knUpdatedAt = current
            this.knUpdatedBy = createdId
            this.knName = name
            this.knSubName = subName
            this.knDesc = desc
            this.isMonday = false
            this.isTuesday = false
            this.isWednesday = false
            this.isThursday = false
            this.isFriday = false
            this.isSaturday = false
            this.isSunday = false
            this.knCycleStartTime = cycleStartTime ?: current
            this.knCycleEndTime = cycleEndTime
            this.isUsed = true
            this.isDel = false
            this.externalKey = externalKey
        })
        return id
    }

    override fun upsertFrequency(frequency: List<FrequencyParams>) {
        // 登录人id
        val patientId = AppSecurityUtil.currentUserIdWithDefault();
        // 当前日期时间
        val current = LocalDateTime.now()

        for (it in frequency) {
            mrFrequencyTable.saveOrUpdate(
                MrFrequency.forInsert(
                    it.id,
                    it.healthPlanId
                ).apply {
                    this.knExplainId = it.explainId
                    this.knFrequencyTime = it.frequencyTime
                    this.knFrequencyTimeUnit = it.frequencyTimeUnit.value
                    this.knFrequencyNum = it.frequencyNum
                    this.knFrequencyNumUnit = it.frequencyNumUnit.value
                    this.knCreatedAt = current
                    this.knCreatedBy = patientId
                }
            )
        }
    }

    override fun getHealthPlanFrequency(ids: List<Id>): Map<Id, List<HealthPlanRule>> {

        // 转map
        val frequencies = mrFrequencyTable.select()
            .where(MrFrequencyTable.KnHealthPlanId `in` ids.map { it.arg })
            .order(MrFrequencyTable.KnId, Order.Asc)
            .find()

        return frequencies.takeIf { it.isNotEmpty() }
            ?.let { list ->
                // 转map
                val frequencieMap = list.mapNotNull { freq ->
                    freq.knFrequencyTime?.let { ft ->
                        freq.knFrequencyTimeUnit?.let { ftu ->
                            freq.knFrequencyNum?.let { fn ->
                                freq.knFrequencyNumUnit?.let { fnu ->
                                    HealthPlanRule(
                                        freq.knId,
                                        ft,
                                        TimeServiceUnit.valueOf(ftu),
                                        fn,
                                        TimeServiceUnit.valueOf(fnu)
                                    )
                                }
                            }
                        }
                    }

                }.associateBy { it.id }
                // 拼关系
                // - 关联下级
                list.forEach { freq ->
                    if (freq.knExplainId != null) {
                        frequencieMap[freq.knExplainId]?.children = frequencieMap[freq.knId]
                    }
                }
                // - 找出顶层对象
                list.filter { it.knExplainId == null && frequencieMap.contains(it.knId) }
                    .groupBy({ it.knHealthPlanId }, { frequencieMap[it.knId] ?: HealthPlanRule.EMPTY })
            } ?: mapOf()
    }

    override fun getListTest(): Map<Id, List<HealthPlanRule>>  {
        // 查询所有
        val frequencyList = mrFrequencyTable.select()
            .order(MrFrequencyTable.KnId, Order.Asc)
            .find()

        val gson = Gson()
        return frequencyList.takeIf { it.isNotEmpty() }
            ?.let { list ->
                println(list)
                // 转map
                val frequencieMap = list.mapNotNull { freq ->
                    freq.knFrequencyTime?.let { ft ->
                        freq.knFrequencyTimeUnit?.let { ftu ->
                            freq.knFrequencyNum?.let { fn ->
                                freq.knFrequencyNumUnit?.let { fnu ->
                                    HealthPlanRule(
                                        freq.knId,
                                        ft,
                                        TimeServiceUnit.valueOf(ftu),
                                        fn,
                                        TimeServiceUnit.valueOf(fnu)
                                    )
                                }
                            }
                        }
                    }

                }.associateBy { it.id }
                println("这是我的打印-----------------------开始")
                println(gson.toJson(frequencieMap))
                println("这是我的打印-----------------------结束")
                // 拼关系
                // - 关联下级
                list.forEach { freq ->
                    if (freq.knExplainId != null) {
                        frequencieMap[freq.knExplainId]?.children = frequencieMap[freq.knId]
                    }
                }
                // - 找出顶层对象
                list.filter { it.knExplainId == null && frequencieMap.contains(it.knId) }
                    .groupBy({ it.knHealthPlanId }, { frequencieMap[it.knId] ?: HealthPlanRule.EMPTY })
            } ?: mapOf()
    }
}