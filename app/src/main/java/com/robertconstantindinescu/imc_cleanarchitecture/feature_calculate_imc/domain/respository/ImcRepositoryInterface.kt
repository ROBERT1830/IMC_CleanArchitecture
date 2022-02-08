package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import kotlinx.coroutines.flow.Flow

interface ImcRepositoryInterface {
    fun getAllImcRecords(): Flow<List<ImcModel>>

    suspend fun getImcRecord(id: Int): ImcModel?
    suspend fun insertImcRecord(imcModel: ImcModel)
    suspend fun deleteImcRecord(imcModel: ImcModel)
}