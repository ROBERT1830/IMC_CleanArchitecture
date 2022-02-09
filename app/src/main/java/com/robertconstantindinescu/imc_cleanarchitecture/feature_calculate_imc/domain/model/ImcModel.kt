package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.Gender
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.LightGreen
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.RedIntense
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.RedOrange
import java.lang.Exception

@Entity(tableName = "imc_table")
data class ImcModel(
    val name: String,
    val gender:Gender,
    val personWeight: Double,
    val peronHeight: Double,
    val date: String,
    val stringResult: String,
    val numericResult: Double,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val colorList = listOf(RedIntense, RedOrange, LightGreen)
    }
}



class InvalidDataException(message:String): Exception(message)

