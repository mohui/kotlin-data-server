package com.bjknrt.kotlin.data.factory.impl

import com.bjknrt.kotlin.data.factory.StudyHandler
import com.bjknrt.kotlin.data.factory.SubjectEnum
import com.bjknrt.kotlin.data.factory.SubjectResult
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
class MathematicsHandler : StudyHandler {
    companion object {
        const val ORDER = 2
    }

    override fun getSubjectDetail(subject: String): List<SubjectResult> {
        return listOf(
            SubjectResult(
                id = BigInteger.valueOf(2),
                name = "数学",
                score = 120
            )
        )
    }

    /**
     * 判断传过来的是否是数学
     */
    override fun getSubject(subject: String): Boolean {
        return SubjectEnum.valueOf(subject) == SubjectEnum.MATHEMATICS
    }

    /**
     * 数学排序为2
     */
    override fun getOrder(): Int {
        return ORDER
    }
}