package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen

import androidx.compose.ui.focus.FocusState
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.Gender

sealed class CalculatorEvent{
    data class EnterName(val value:String): CalculatorEvent()
    data class EnterWeight(val value:String): CalculatorEvent()
    data class EnterHeight(val value:String): CalculatorEvent()
    //data class GetResult(val value:String): CalculatorEvent()  --> I don't think that this could be an event because the user doesn't interact with that field
    data class CalculateImc(val weight:Double, val height:Double):CalculatorEvent()

    data class ChangeNameFocus(val focusState: FocusState): CalculatorEvent()
    data class ChangeWeightFocus(val focusState: FocusState): CalculatorEvent()
    data class ChangeHeightFocus(val focusState: FocusState): CalculatorEvent()

    data class ChangeColor(val color: Int, val numericResult:  Double, val gender:Gender): CalculatorEvent()
    object SaveImcRecord: CalculatorEvent()
    data class GenderChange(val gender: Gender):CalculatorEvent()
}