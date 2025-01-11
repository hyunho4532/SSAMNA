package com.asetec.domain.dto.user

import kotlinx.serialization.Serializable


/**
 * AuthField: 사용자가 입력한 정보들을 저장하는 데이터 클래스
 */
@Serializable
data class UserState (
    val age: Float = 0f,
    val recentExerciseCheck: Boolean? = null,
    val recentExerciseName: String? = null,
    val recentWorkingJog: Boolean? = null,
    val targetPeriod: Boolean? = null
)