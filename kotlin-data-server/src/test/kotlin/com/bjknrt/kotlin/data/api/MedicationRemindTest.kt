package com.bjknrt.kotlin.data.api

import com.bjknrt.kotlin.data.vo.BatchDelParam
import com.bjknrt.kotlin.data.vo.Inner
import com.bjknrt.kotlin.data.vo.UpdBatchStatusParams
import com.bjknrt.kotlin.data.vo.UpdStatusParams
import com.bjknrt.kotlin.data.vo.UpsertParams
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import com.bjknrt.kotlin.data.AbstractContainerBaseTest

class MedicationRemindTest : AbstractContainerBaseTest() {

    @Autowired
    lateinit var api: MedicationRemindApi

    /**
     * To test MedicationRemindApi.batchDel
     */
    @Test
    fun batchDelTest() {
        val batchDelParam: BatchDelParam = TODO()
        val response: Unit = api.batchDel(batchDelParam)

        // TODO: test validations
    }

    /**
     * To test MedicationRemindApi.del
     */
    @Test
    fun delTest() {
        val body: java.math.BigInteger = TODO()
        val response: kotlin.Boolean = api.del(body)

        // TODO: test validations
    }

    /**
     * To test MedicationRemindApi.list
     */
    @Test
    fun listTest() {
        val response: List<Inner> = api.list()

        // TODO: test validations
    }

    /**
     * To test MedicationRemindApi.updBatchStatus
     */
    @Test
    fun updBatchStatusTest() {
        val updBatchStatusParams: UpdBatchStatusParams = TODO()
        val response: Unit = api.updBatchStatus(updBatchStatusParams)

        // TODO: test validations
    }

    /**
     * To test MedicationRemindApi.updStatus
     */
    @Test
    fun updStatusTest() {
        val updStatusParams: UpdStatusParams = TODO()
        val response: kotlin.Boolean = api.updStatus(updStatusParams)

        // TODO: test validations
    }

    /**
     * To test MedicationRemindApi.upsert
     */
    @Test
    fun upsertTest() {
        val upsertParams: UpsertParams = TODO()
        val response: kotlin.Boolean = api.upsert(upsertParams)

        // TODO: test validations
    }
}
