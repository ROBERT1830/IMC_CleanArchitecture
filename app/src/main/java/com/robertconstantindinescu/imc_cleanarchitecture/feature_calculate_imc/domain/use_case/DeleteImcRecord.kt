package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.repository.ImcRepositoryImpl
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository.ImcRepositoryInterface


class DeleteImcRecord(
    private val repo: ImcRepositoryInterface
) {
    suspend operator fun invoke(imcModel: ImcModel){
        repo.deleteImcRecord(imcModel)
    }
}