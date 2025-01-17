package com.asetec.domain.model.state

import kotlinx.serialization.Serializable

@Serializable
data class Activate(
    val index: String = "",
    val name: String = "",
    val description: String = "",
    val assets: String = "",


    /** 활동 종류 **/
    var activateResId: Int = 2131165314,
    var activateName: String = "달리기",

    /** 측정 상태 **/
    var activateButtonName: String = "측정하기!",

    /** 만보기 걸음 수 **/
    var pedometerCount: Int = 0
)