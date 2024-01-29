package com.surajrathod.bcawave.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surajrathod.bcawave.db.models.ProgramEntity

@Dao
interface ProgramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(programEntity: ProgramEntity)

    @Delete
    suspend fun delete(programEntity: ProgramEntity)

    @Query("select * from ProgramEntity order by id desc")
    fun getAllProgram(): LiveData<List<ProgramEntity>>


    @Query("select * from ProgramEntity where id = :postId1")
    fun getProgramById(postId1: Int): ProgramEntity


    @Query("select exists(select * from ProgramEntity where id = :id)")
    fun isFav(id: Int): Boolean

    @Query("delete from ProgramEntity where id = :id")
    fun removeFav(id: Int)
}