package com.bjknrt.kotlin.data.api

import com.bjknrt.kotlin.data.vo.BatchDelParam
import com.bjknrt.framework.api.vo.Id
import com.bjknrt.kotlin.data.vo.Inner
import com.bjknrt.kotlin.data.vo.UpdBatchStatusParams
import com.bjknrt.kotlin.data.vo.UpdStatusParams
import com.bjknrt.kotlin.data.vo.UpsertParams

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
interface MedicationRemindApi {


    /**
     * 用药提醒批量删除
     * 
     *
     * @param batchDelParam
     * @return Unit
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/medicationRemind/batchDel"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun batchDel(@Valid batchDelParam: BatchDelParam): Unit


    /**
     * 用药提醒删除
     * 
     *
     * @param body
     * @return kotlin.Boolean
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/medicationRemind/del"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun del(@Valid body: java.math.BigInteger): kotlin.Boolean


    /**
     * 用药提醒列表
     * 
     *
     * @return List<Inner>
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/medicationRemind/list"],
        produces = ["application/json"]
    )
    fun list(): List<Inner>


    /**
     * 用药提醒批量停用启用
     * 
     *
     * @param updBatchStatusParams
     * @return Unit
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/medicationRemind/updBatchStatus"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updBatchStatus(@Valid updBatchStatusParams: UpdBatchStatusParams): Unit


    /**
     * 用药提醒停用启用
     * 
     *
     * @param updStatusParams
     * @return kotlin.Boolean
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/medicationRemind/updStatus"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updStatus(@Valid updStatusParams: UpdStatusParams): kotlin.Boolean


    /**
     * 药品提醒添加和修改
     * 
     *
     * @param upsertParams
     * @return kotlin.Boolean
     */
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/medicationRemind/upsert"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun upsert(@Valid upsertParams: UpsertParams): kotlin.Boolean
}
