package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case

import android.app.Application
import com.robertconstantindinescu.imc_cleanarchitecture.R
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.data.repository.ImcRepositoryImpl
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.InvalidDataException
import kotlin.jvm.Throws

class AddImcRecord(
    private val application: Application,
    private val repo: ImcRepositoryImpl
) {

    @Throws(InvalidDataException::class)
    suspend operator fun invoke(imcModel: ImcModel){

        if (imcModel.peronHeight.toString().isBlank()){
            throw InvalidDataException(application.resources.getString(R.string.empty_height))
        }
        if (imcModel.personWeight.toString().isBlank()){
            throw InvalidDataException(application.resources.getString(R.string.empty_weight))
        }

        repo.insertImcRecord(imcModel)
    }
}