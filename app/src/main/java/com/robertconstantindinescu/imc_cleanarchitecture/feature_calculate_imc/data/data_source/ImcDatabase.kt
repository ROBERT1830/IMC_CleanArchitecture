package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.data_source

import androidx.room.Database
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel

@Database(
    entities = [ImcModel::class],
    version = 1
)
abstract class ImcDatabase {
    abstract val imcDao: ImcDao

    companion object{
        const val DATABASE_NAME = "imc_db"
    }
}