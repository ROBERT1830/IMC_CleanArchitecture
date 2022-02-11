package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen

import android.util.Log
import android.widget.Space
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.components.GenderSection
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.components.ResultSection
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.components.TransparentHintTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    CalculatorScreen(
        navController = rememberNavController(),
        color = Color.Transparent.toArgb(),
    )
}

@Composable
fun CalculatorScreen(
    navController: NavController,
    color: Int,
    viewModel: CalculatorViewModel = hiltViewModel()
) {

    //state variables

    val nameState = viewModel.nameState.value

    val weightState = viewModel.weightState.value


    val heightState = viewModel.heightState.value


    val genderState = viewModel.genderState.value

    val stringResultState = viewModel.stringResultState.value


    val numericResultState = viewModel.numericResult.value

    Log.d("numericResult", numericResultState.toString())

    val colorState = viewModel.colorState.value


    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val backGroundAnimatable = remember {
        Animatable(
            //Color(((if (color != -1) color else Color.Transparent) as Int))
            Color(if (color != -1) color else viewModel.colorState.value)
        )
    }

    //compose only Once emitted value
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                CalculatorViewModel.SingleUiEvent.SaveNote -> {
                    navController.navigateUp()
                }
                is CalculatorViewModel.SingleUiEvent.ShowSnackbar -> {
                    scope.launch(Dispatchers.Main) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }
            }
        }
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.d("numericResultState", numericResultState.text.toString())
                    if(numericResultState.text.isNotBlank()){
                        viewModel.onEvent(
                            CalculatorEvent.ChangeColor(
                                color = colorState,
                                numericResult = numericResultState.text.toDouble(),
                                genderState
                                // TODO: 10/2/22 change the gender state
                            )
                        )
                    }

//                    scope.launch(Dispatchers.Main) {
//                        backGroundAnimatable.animateTo(
//                            targetValue = Color(colorState),
//                            animationSpec = tween(durationMillis = 500)
//                        )
//                    }
                    viewModel.onEvent(CalculatorEvent.SaveImcRecord)


                },
                backgroundColor = MaterialTheme.colors.primary

            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save record")

            }
        },
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            TransparentHintTextField(
                text = nameState.text,
                hintText = nameState.hintText,
                onValueChange = {
                    viewModel.onEvent(CalculatorEvent.EnterName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(CalculatorEvent.ChangeNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5

            )
            Spacer(modifier = Modifier.height(10.dp))

            TransparentHintTextField(
                text = weightState.text,
                hintText = weightState.hintText,
                onValueChange = {
                    viewModel.onEvent(CalculatorEvent.EnterWeight(it))
                },
                onFocusChange = {
                    viewModel.onEvent(CalculatorEvent.ChangeWeightFocus(it))
                },
                isHintVisible = weightState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5

            )
            Spacer(modifier = Modifier.height(10.dp))

            TransparentHintTextField(
                text = heightState.text,
                hintText = heightState.hintText,
                onValueChange = {
                    viewModel.onEvent(CalculatorEvent.EnterHeight(it))
                },
                onFocusChange = {
                    viewModel.onEvent(CalculatorEvent.ChangeHeightFocus(it))
                },
                isHintVisible = heightState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5

            )
            Spacer(modifier = Modifier.height(10.dp))

            GenderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                gender = genderState,
                onGenderChange = {
                    viewModel.onEvent(CalculatorEvent.GenderChange(it))
                }

            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.padding(start = 16.dp)) {
                Button(
                    onClick = {
                        viewModel.onEvent(
                            CalculatorEvent.CalculateImc(
                                weightState.text.toDouble(),
                                heightState.text.toDouble(),
                                genderState
                            )
                        )
                    }
                ) {
                    Text(text = "Calculate", style = MaterialTheme.typography.button)

                }
            }


            Spacer(modifier = Modifier.height(10.dp))
            ResultSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                resultString = stringResultState.text,
                resultNumber = numericResultState.text
            )
        }


    }

}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun TextFieldsPreview(){
//    TextFields(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        nameState = "Robert",
//        weightState = "72.9",
//        heightState = "179"
//
//    )
//
//}
//
//@Composable
//fun TextFields(
//    modifier: Modifier = Modifier,
//    nameState: String,
//    weightState: String,
//    heightState: String,
//
//
//){
//    Column(
//        modifier = modifier
//    ) {
//        TransparentHintTextField(
//            text = nameState,
//            hintText =
//        )
//    }
//
//
//}






























