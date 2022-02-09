package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen

data class CalculatorResultState(
    val text:String = "",
    val hint:String = "",
    val isHintVisible: Boolean? = false
)