package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.ImcOrder

sealed class ImcDataEvent {

    data class Order(val imcOrderType: ImcOrder): ImcDataEvent()
    data class DeleteImcRecord(val imcModel: ImcModel):ImcDataEvent()

    object ToggleOrderSection: ImcDataEvent()
    object RestoreNote:ImcDataEvent()




}