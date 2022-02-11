package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case

import kotlin.math.pow

/**
 * This use case doesn't need the repo becuse will not acces database
 */
class CalculateImc {


    companion object{
        operator fun invoke(weight: Double, height: Double): Double {

            //first calculate the result
            return weight / height.pow(2.0)


        }
    }


}