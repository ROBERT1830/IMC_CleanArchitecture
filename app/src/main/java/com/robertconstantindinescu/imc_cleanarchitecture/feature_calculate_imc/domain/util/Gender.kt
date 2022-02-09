package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util

sealed class Gender{
    object Female: Gender()
    object Male: Gender()
}
