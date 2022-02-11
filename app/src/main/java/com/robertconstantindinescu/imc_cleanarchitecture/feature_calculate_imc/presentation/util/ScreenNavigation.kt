package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.util

sealed class ScreenNavigation(val route:String){
    object ImcDataScreen: ScreenNavigation("imc_data_screen")
    object CalculatorScreen: ScreenNavigation("add_edit_screen")
}
