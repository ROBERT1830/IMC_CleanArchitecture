package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen

import androidx.compose.ui.focus.FocusState

sealed class CalculatorEvent{
    data class EnterName(val value:String): CalculatorEvent()
    data class EnterWeight(val value:String): CalculatorEvent()
    data class EnterHeight(val value:String): CalculatorEvent()
    data class GetResult(val value:String): CalculatorEvent()

    data class ChangeNameFocus(val focusState: FocusState): CalculatorEvent()
    data class ChangeWeightFocus(val focusState: FocusState): CalculatorEvent()
    data class ChangeHeightFocus(val focusState: FocusState): CalculatorEvent()

    data class ChangeColor(val color: Int): CalculatorEvent()
    object SaveImcRecord: CalculatorEvent()
    object GenderChange:CalculatorEvent()
}