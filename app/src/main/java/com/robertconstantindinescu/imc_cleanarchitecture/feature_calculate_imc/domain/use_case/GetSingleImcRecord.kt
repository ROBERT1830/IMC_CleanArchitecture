package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository.ImcRepositoryInterface

class GetSingleImcRecord(
    private val repo: ImcRepositoryInterface
) {

    suspend operator fun invoke(id:Int):ImcModel?{
        return repo.getImcRecord(id)
    }
}