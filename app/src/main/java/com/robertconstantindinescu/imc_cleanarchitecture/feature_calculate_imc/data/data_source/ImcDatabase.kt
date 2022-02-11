package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.GenderTypeConverter
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel

@Database(
    entities = [ImcModel::class],
    version = 1
)

//@TypeConverters(GenderTypeConverter::class)
abstract class ImcDatabase: RoomDatabase() {
    abstract val imcDao: ImcDao

    companion object{
        const val DATABASE_NAME = "imc_db"
    }
}