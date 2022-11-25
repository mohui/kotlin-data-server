package com.bjknrt.kotlin.data.factory.impl

import com.bjknrt.kotlin.data.factory.StudyHandler
import com.bjknrt.kotlin.data.factory.SubjectEnum
import com.bjknrt.kotlin.data.factory.SubjectResult
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
class ChineseHandler: StudyHandler {
    companion object {
        const val ORDER = 1
    }

    override fun getSubjectDetail(subject: String): List<SubjectResult> {
        return listOf(
            SubjectResult(
                id = BigInteger.valueOf(1),
                name = "语文",
                score = 100
            )
        )
    }

    /**
     * 判断传过来的是否是语文
     */
    override fun getSubject(subject: String): Boolean {
        return SubjectEnum.valueOf(subject) == SubjectEnum.CHINESE
    }

    /**
     * 语文默认排序为1
     */
    override fun getOrder(): Int {
        return ORDER
    }
}