package com.bjknrt.kotlin.data.factory

import org.springframework.stereotype.Component

@Component
class StudyFactory(
    studyHandlerList: List<StudyHandler>
) {
    // 排序
    private val studyList = studyHandlerList.sortedByDescending { it.getOrder() }

    // 根据传过来的参数取出相应的科目
    private fun checkSubject(subject: String): StudyHandler? {
        return studyList.firstOrNull{ it.getSubject(subject) }
    }

    // 根据科目获取详情
    fun getSubject(subject: String): List<SubjectResult> {
        return this.checkSubject(subject)?.getSubjectDetail(subject)?: listOf()
    }
}