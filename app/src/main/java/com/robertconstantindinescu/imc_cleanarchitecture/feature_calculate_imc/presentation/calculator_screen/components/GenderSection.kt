package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.Gender


@Composable
fun GenderSection(
    modifier: Modifier = Modifier,
    gender: Gender = Gender.Female,
    onGenderChange: (Gender) -> Unit
){
    Log.d("GenderSection", gender.toString())
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultGenderRadioButton(
                text = "Female",
                selected = gender is Gender.Female,
                onSelect = {
                    onGenderChange(Gender.Female)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultGenderRadioButton(
                text = "Male",
                selected = gender is Gender.Male,
                onSelect = {onGenderChange(Gender.Male)}
            )
        }
    }
}