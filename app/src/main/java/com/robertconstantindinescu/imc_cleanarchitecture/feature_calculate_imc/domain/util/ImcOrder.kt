package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util

sealed class ImcOrder(val ascDescOrder: AscDescOrder){
    class Color(ascDescOrder: AscDescOrder): ImcOrder(ascDescOrder)
    class Date(ascDescOrder: AscDescOrder): ImcOrder(ascDescOrder)

    fun copy(orderType: AscDescOrder): ImcOrder{
        return when(this){
            is Color -> Color(orderType)
            is Date -> Date(orderType)
        }
    }

}
