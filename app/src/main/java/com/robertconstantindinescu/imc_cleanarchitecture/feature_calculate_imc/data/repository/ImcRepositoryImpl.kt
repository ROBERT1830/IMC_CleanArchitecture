package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.repository

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.data_source.ImcDao
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository.ImcRepositoryInterface
import kotlinx.coroutines.flow.Flow

class ImcRepositoryImpl(
    private val imcDao: ImcDao
): ImcRepositoryInterface {

    override fun getAllImcRecords(): Flow<List<ImcModel>> {
        return imcDao.getAllImcRecords()

    }

    override suspend fun getImcRecord(id: Int): ImcModel? {
        return imcDao.getImcRecord(id)
    }

    override suspend fun insertImcRecord(imcModel: ImcModel) {
        imcDao.insertImcRecord(imcModel)
    }

    override suspend fun deleteImcRecord(imcModel: ImcModel) {
        imcDao.deleteImcRecord(imcModel)
    }
}