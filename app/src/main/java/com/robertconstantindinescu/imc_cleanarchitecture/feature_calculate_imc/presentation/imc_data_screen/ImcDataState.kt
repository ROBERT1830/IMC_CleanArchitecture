package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.AscDescOrder
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.ImcOrder

/**
 * Define the states of the imc data screen.
 */
data class ImcDataState(
  val imcList: List<ImcModel> = emptyList(),
  val orderType: ImcOrder = ImcOrder.Date(AscDescOrder.Descending),
  val isOrderMenuVisible: Boolean = false
)