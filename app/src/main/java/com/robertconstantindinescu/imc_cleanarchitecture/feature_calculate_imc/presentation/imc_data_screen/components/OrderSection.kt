package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.AscDescOrder
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.ImcOrder

@Preview(showBackground = true)
@Composable
fun MyPreview() {
    OrderSection(modifier = Modifier, ImcOrder.Date(AscDescOrder.Descending))
}


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    imcOrderType: ImcOrder = ImcOrder.Date(AscDescOrder.Descending),
    onOrderChange: (ImcOrder) -> Unit = {}
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Color",
                selected = imcOrderType is ImcOrder.Color,
                onSelect = {
                    onOrderChange(ImcOrder.Color(imcOrderType.ascDescOrder))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = imcOrderType is ImcOrder.Date,
                onSelect = {
                    onOrderChange(ImcOrder.Date(imcOrderType.ascDescOrder))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = imcOrderType.ascDescOrder is AscDescOrder.Ascending,
                onSelect = {
                    onOrderChange(imcOrderType.copy(
                        AscDescOrder.Ascending
                    ))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = imcOrderType.ascDescOrder is AscDescOrder.Descending,
                onSelect = {
                    onOrderChange(imcOrderType.copy(
                        AscDescOrder.Descending
                    ))
                }
            )
        }

    }


}