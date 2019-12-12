package io.mediaportal.ironsource.model


data class ButtonToAction(
    val type: String,
    val enabled: Boolean,
    val priority: Int,
    val valid_days: Array<Int>,
    val cool_down: Long
)