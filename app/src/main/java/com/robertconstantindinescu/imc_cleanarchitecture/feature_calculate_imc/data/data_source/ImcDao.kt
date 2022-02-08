package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.data_source

import androidx.room.*
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ImcDao {

    @Query("SELECT * FROM imc_table")
    fun getAllImcRecords():Flow<List<ImcModel>>

    @Query("SELECT * FROM imc_table WHERE id = :id")
    suspend fun getImcRecord(id: Int): ImcModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImcRecord(imc: ImcModel)

    @Delete()
    suspend fun deleteImcRecord(imc: ImcModel)
}