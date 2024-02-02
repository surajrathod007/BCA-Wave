package com.surajrathod.bcawave.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.surajrathod.bcawave.ui.dashboard.home.ProgramItemData
import java.io.Serializable

@Entity
data class ProgramEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val content: String = "",
    val sem: String = "",
    val sub: String = "",
    val unit: String = ""
) : Serializable{
    fun toProgramItemData(isFav: Boolean): ProgramItemData {
        return ProgramItemData(isFav, sub, unit, sem, id, title, content)
    }
}