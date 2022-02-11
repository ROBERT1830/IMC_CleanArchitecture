package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.format.TextStyle


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TransparentHintTextFieldPreview() {
    TransparentHintTextField(
        "",
        "Texto aqui...",
        modifier = Modifier,


    )
}

@Composable
fun TransparentHintTextField(
    text: String,
    hintText: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    singleLine: Boolean = true,
    textStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(),
    onValueChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {}
) {
    Row {
        Card(modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .requiredHeight(60.dp),
            elevation = 10.dp) {
            BasicTextField(
                value = text,
                onValueChange = {
                    onValueChange(it)
                },
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it)
                    }
                    .padding(vertical = 10.dp, horizontal = 5.dp)

            )
            if (isHintVisible){
                Text(text = hintText, style = textStyle, color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .padding(5.dp))
            }

            // TODO: 10/2/22 take care of the hint because is not working ok

        }

    }


}