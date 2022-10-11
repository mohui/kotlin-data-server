package com.bjknrt.kotlin.data.api

import com.bjknrt.kotlin.data.vo.BatchTypeClockInParams
import com.bjknrt.kotlin.data.vo.FrequencyGetClockInParam
import com.bjknrt.kotlin.data.vo.FrequencyHealthAllParam
import com.bjknrt.kotlin.data.vo.FrequencyHealthParams
import com.bjknrt.kotlin.data.vo.HealthPlan
import com.bjknrt.kotlin.data.vo.HealthPlanFrequencyGetClockInParam
import com.bjknrt.kotlin.data.vo.HealthPlanMain
import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.PatientIdGetListParam
import com.bjknrt.kotlin.data.vo.UpsertHealthFrequencyResult

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated

import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

import kotlin.collections.List
import kotlin.collections.Map

@Validated
interface HealthPlanApi {


    /**
     * 根据id批量打卡接口
     * 
     *
     * @param id
     * @return List<HealthPlan>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/batchIdClockIn"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun batchIdClockIn(@Valid id: kotlin.collections.List<Id>)


    /**
     * 根据type批量打卡接口
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/batchIdTypeClockIn"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun batchIdTypeClockIn()


    /**
     * 打卡
     * 
     *
     * @param body
     * @return List<HealthPlan>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/clockIn"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun clockIn(@Valid body: java.math.BigInteger): List<HealthPlan>


    /**
     * 根据健康计划id和规则获取打卡记录
     * 
     *
     * @param frequencyGetClockInParam
     * @return kotlin.Int
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/frequencyGetClockIn"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun frequencyGetClockIn(@Valid frequencyGetClockInParam: FrequencyGetClockInParam): kotlin.Int


    /**
     * 根据健康计划id和规则id获取打卡记录
     * 
     *
     * @param healthPlanFrequencyGetClockInParam
     * @return kotlin.Int
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/healthPlanFrequencyGetClockIn"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun healthPlanFrequencyGetClockIn(@Valid healthPlanFrequencyGetClockInParam: HealthPlanFrequencyGetClockInParam): kotlin.Int


    /**
     * 根据健康计划id获取健康计划列表
     * 
     *
     * @param id
     * @return List<HealthPlanMain>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/idGetList"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun idGetList(@Valid id: kotlin.collections.List<Id>): List<HealthPlanMain>


    /**
     * 健康计划列表
     * 
     *
     * @return List<HealthPlan>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/list"],
        produces = ["application/json"]
    )
    fun list(): List<HealthPlan>


    /**
     * 根据患者ID获取健康计划列表
     * 
     *
     * @param patientIdGetListParam
     * @return List<HealthPlanMain>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/patientIdGetList"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun patientIdGetList(@Valid patientIdGetListParam: PatientIdGetListParam): List<HealthPlanMain>


    /**
     * 健康计划添加和修改
     * 
     *
     * @param frequencyHealthParams
     * @return UpsertHealthFrequencyResult
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/upsertFrequencyHealth"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun upsertFrequencyHealth(@Valid frequencyHealthParams: FrequencyHealthParams)


    /**
     * 根据type批量添加和修改提醒
     * 
     *
     * @param frequencyHealthAllParam
     * @return List<UpsertHealthFrequencyResult>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/healthPlan/upsertTypeFrequencyHealth"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun upsertTypeFrequencyHealth(@Valid frequencyHealthAllParam: FrequencyHealthAllParam): List<UpsertHealthFrequencyResult>
}
