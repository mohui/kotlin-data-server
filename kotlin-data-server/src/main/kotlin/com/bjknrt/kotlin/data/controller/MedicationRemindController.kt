package com.bjknrt.kotlin.data.controller

import com.bjknrt.kotlin.data.api.MedicationRemindApi
import com.bjknrt.framework.api.AppBaseController
import com.bjknrt.kotlin.data.factory.StudyFactory
import com.bjknrt.kotlin.data.vo.*
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@RestController("com.bjknrt.kotlin.data.api.MedicationRemindController")
class MedicationRemindController(
    val studyFactory: StudyFactory
) : AppBaseController(), MedicationRemindApi {
    override fun batchDel(batchDelParam: BatchDelParam) {
        println(Calendar.WEEK_OF_YEAR)
        println("hello word")
    }

    override fun del(body: BigInteger): Boolean {
        var subject: String = "CHINESE"
        if (body == BigInteger.valueOf(2)) subject = "MATHEMATICS"
        studyFactory.getSubject(subject)


        return false
    }

    override fun list(): List<Inner> {
        return listOf(
            Inner(
                id = BigInteger.valueOf(10000000),
                drugName = "阿莫西林",
                time = LocalTime.now(),
                status = true,
                weeks = listOf(Week.MONDAY)
            )
        )
    }

    override fun updBatchStatus(updBatchStatusParams: UpdBatchStatusParams) {
    }

    override fun updStatus(updStatusParams: UpdStatusParams): Boolean {
        return true
    }

    override fun upsert(upsertParams: UpsertParams): Boolean {
        Calendar.WEEK_OF_YEAR
        val time = LocalDateTime.now();
        return true
    }

}