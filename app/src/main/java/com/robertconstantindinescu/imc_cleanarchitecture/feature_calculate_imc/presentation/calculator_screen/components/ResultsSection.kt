package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultSectionPreview(){
    ResultSection(
        modifier = Modifier,
        "Results",
        "189"
    )
}

@Composable
fun ResultSection(
    modifier: Modifier = Modifier,
    resultString:String,
    resultNumber: String
){

    Column {
        Row(modifier = modifier,
            horizontalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(100.dp)
                .padding(10.dp),
                elevation = 10.dp,
            ) {

                Text(text = resultString, style = MaterialTheme.typography.h6, textAlign = TextAlign.Center, modifier = Modifier.wrapContentHeight())

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = modifier,
            horizontalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(100.dp)
                .padding(10.dp),
                elevation = 10.dp,
            ) {

                Text(text = resultNumber, style = MaterialTheme.typography.h6, textAlign = TextAlign.Center,
                    modifier = Modifier.wrapContentHeight())

            }
        }
    }



}