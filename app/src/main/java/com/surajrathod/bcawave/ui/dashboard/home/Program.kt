package com.surajrathod.bcawave.ui.dashboard.home

import com.surajrathod.bcawave.db.models.ProgramEntity

data class Program(
    val sub: String = "",
    val unit: String = "",
    val sem: String = "",
    val id: Int = 0,
    val title: String = "",
    val content: String = ""
) {
    fun toProgramEntity(): ProgramEntity {
        return ProgramEntity(id, title, content, sem, sub)
    }

    fun toProgramItemData(isFav: Boolean): ProgramItemData {
        return ProgramItemData(isFav, sub, unit, sem, id, title, content)
    }


}