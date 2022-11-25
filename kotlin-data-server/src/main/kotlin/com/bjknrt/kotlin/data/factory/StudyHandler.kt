package com.bjknrt.kotlin.data.factory

interface StudyHandler {
    /**
     * 获取科目
     */
    fun getSubjectDetail(subject: String): List<SubjectResult>

    /**
     * 获取科目code
     */
    fun getSubject(subject: String): Boolean

    /**
     * 排序
     */
    fun getOrder(): Int
}