package com.surajrathod.bcawave.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.surajrathod.bcawave.db.dao.ProgramDao
import com.surajrathod.bcawave.db.models.ProgramEntity

@Database(entities = [ProgramEntity::class], version = 1)
abstract class BcaWaveDatabase : RoomDatabase() {
    abstract fun programsDao(): ProgramDao

}