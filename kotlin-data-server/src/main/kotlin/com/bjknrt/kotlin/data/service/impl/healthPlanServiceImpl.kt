package com.bjknrt.kotlin.data.service.impl

import com.bjknrt.framework.api.vo.Id
import com.bjknrt.framework.util.AppIdUtil
import com.bjknrt.kotlin.data.MrFrequency
import com.bjknrt.kotlin.data.MrFrequencyTable
import com.bjknrt.kotlin.data.MrHealthPlan
import com.bjknrt.kotlin.data.MrHealthPlanTable
import com.bjknrt.kotlin.data.service.HealthPlanService
import com.bjknrt.kotlin.data.vo.FrequencyHealthParams
import com.bjknrt.kotlin.data.vo.FrequencyParams
import com.bjknrt.user.permission.centre.security.AppSecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.time.LocalDateTime

@Service
class HealthPlanServiceImpl(
    val table: MrHealthPlanTable,
    val mrFrequencyTable: MrFrequencyTable
): HealthPlanService {
    override fun clockIn(id: BigInteger) {
        TODO("Not yet implemented")
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
}