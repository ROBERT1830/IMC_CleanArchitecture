package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.Gender

/**
 * Here we are defining a state object
 */
data class CalculatorTextFieldState(
    val text:String = "",
//    val stringResult:String = "",
//    val numericResult: String = "",
    val hintText:String = "",
    //val gender: Gender = Gender.Female,
    val isHintVisible: Boolean = false,
    //val isDialogVisible: Boolean? = false
)
