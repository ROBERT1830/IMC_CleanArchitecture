package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.wraper_use_cases

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.AddImcRecord
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.DeleteImcRecord
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.GetImcRecords

data class CalculateImcUseCases(

    val getImcRecords: GetImcRecords,
    val addImcRecord: AddImcRecord,
    val deleteImcRecord: DeleteImcRecord

)
