package com.surajrathod.bcawave.ui.dashboard.home

data class ProgramItemData(
    val isFav: Boolean = false, val sub: String = "",
    val unit: String = "",
    val sem: String = "",
    val id: Int = 0,
    val title: String = "",
    val content: String = ""
) {
    fun toProgram(): Program {
        return Program(sub, unit, sem, id, title, content)
    }
}
