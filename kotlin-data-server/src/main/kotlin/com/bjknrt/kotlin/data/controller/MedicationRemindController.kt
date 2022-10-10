package com.bjknrt.kotlin.data.controller

import com.bjknrt.kotlin.data.api.MedicationRemindApi
import com.bjknrt.framework.api.AppBaseController
import com.bjknrt.kotlin.data.vo.*
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import java.time.LocalDateTime
import java.util.*

@RestController("com.bjknrt.kotlin.data.api.MedicationRemindController")
class MedicationRemindController() : AppBaseController(), MedicationRemindApi {
    override fun batchDel(batchDelParam: BatchDelParam) {
        TODO("Not yet implemented")





    }

    override fun del(body: BigInteger): Boolean {
        println(Calendar.WEEK_OF_YEAR)


        return false
    }

    override fun list(): List<Inner> {
        TODO("Not yet implemented")
    }

    override fun updBatchStatus(updBatchStatusParams: UpdBatchStatusParams) {
        TODO("Not yet implemented")
    }

    override fun updStatus(updStatusParams: UpdStatusParams): Boolean {
        TODO("Not yet implemented")
    }

    override fun upsert(upsertParams: UpsertParams): Boolean {
        Calendar.WEEK_OF_YEAR
        val time = LocalDateTime.now();
        return true
    }

}