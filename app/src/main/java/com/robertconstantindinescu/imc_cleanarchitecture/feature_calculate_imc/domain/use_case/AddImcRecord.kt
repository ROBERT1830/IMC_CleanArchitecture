package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case

import android.app.Application
import com.robertconstantindinescu.imc_cleanarchitecture.R
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.repository.ImcRepositoryImpl
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.InvalidDataException
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository.ImcRepositoryInterface
import kotlin.jvm.Throws

class AddImcRecord(
    private val application: Application,
    private val repo: ImcRepositoryInterface
) {

    @Throws(InvalidDataException::class)
    suspend operator fun invoke(imcModel: ImcModel){

        if(imcModel.name.isBlank()){
            throw InvalidDataException("The name must not be empty")
        }

        if (imcModel.personWeight == 0.0){
            throw InvalidDataException("The weight must not be empty")
        }

        if (imcModel.peronHeight == 0.0){
            throw InvalidDataException("The height must not be empty")
        }

        if (imcModel.numericResult == 0.0 || imcModel.stringResult.isBlank()){
            throw InvalidDataException("First you need to calculate you imc")
        }


        repo.insertImcRecord(imcModel)
    }
}