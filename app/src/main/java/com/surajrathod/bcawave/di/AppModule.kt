package com.surajrathod.bcawave.di

import android.content.Context
import androidx.room.Room
import com.surajrathod.bcawave.db.BcaWaveDatabase
import com.surajrathod.bcawave.db.dao.ProgramDao
import com.surajrathod.bcawave.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideBcaWaveDatabase(
        @ApplicationContext appContext: Context
    ): BcaWaveDatabase {
        return Room.databaseBuilder(
            appContext,
            BcaWaveDatabase::class.java,
            AppConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideProgramDao(bcaWaveDatabase: BcaWaveDatabase): ProgramDao {
        return bcaWaveDatabase.programsDao()
    }


}