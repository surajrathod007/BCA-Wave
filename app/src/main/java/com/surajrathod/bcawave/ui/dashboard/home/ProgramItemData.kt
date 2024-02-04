package com.surajrathod.bcawave.ui.dashboard.home

import com.surajrathod.bcawave.db.models.ProgramEntity

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

    fun toProgramEntity(): ProgramEntity {
        return ProgramEntity(id, title, content, sem, sub)
    }

    fun getTempData(): ProgramItemData {
        return ProgramItemData(false, "Sub", "Unit", "Sem", 0, "Sample title", "Sample content")
    }
}
