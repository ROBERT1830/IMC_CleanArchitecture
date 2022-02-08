package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.LightGreen
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.RedIntense
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.RedOrange
import java.lang.Exception

@Entity(tableName = "imc_table")
data class ImcModel(
    val name: String,
    val age: String,
    val personWeight: Double,
    val peronHeight: Double,
    val timeStamp: Long,
    val result: String,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val colorList = listOf(RedOrange, RedIntense, LightGreen)
    }
}

class InvalidDataException(message:String): Exception(message)

