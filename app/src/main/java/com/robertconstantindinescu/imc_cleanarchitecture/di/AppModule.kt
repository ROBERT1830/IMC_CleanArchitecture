package com.robertconstantindinescu.imc_cleanarchitecture.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.data_source.ImcDatabase
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.data_source.ImcDatabase.Companion.DATABASE_NAME
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.repository.ImcRepositoryImpl
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository.ImcRepositoryInterface
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.AddImcRecord
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.DeleteImcRecord
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.GetImcRecords
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.wraper_use_cases.CalculateImcUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesImcDatabase(application: Application): ImcDatabase{
        return Room.databaseBuilder(
            application,
            ImcDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesImcRepository(imcDatabase: ImcDatabase): ImcRepositoryInterface{
        return ImcRepositoryImpl(imcDatabase.imcDao)
    }

    @Provides
    @Singleton
    fun provideImcUseCases(repositoryInterface: ImcRepositoryInterface): CalculateImcUseCases{
        return CalculateImcUseCases(
            getImcRecords = GetImcRecords(repositoryInterface),
            addImcRecord = AddImcRecord(application = Application(), repo =  repositoryInterface),
            deleteImcRecord = DeleteImcRecord(repositoryInterface),
        )
    }

}