package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util

sealed class AscDescOrder{
    object Ascending: AscDescOrder()
    object Descending: AscDescOrder()
}
