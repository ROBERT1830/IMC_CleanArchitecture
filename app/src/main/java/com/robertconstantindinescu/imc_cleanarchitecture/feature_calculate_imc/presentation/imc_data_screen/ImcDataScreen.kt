package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.wraper_use_cases.CalculateImcUseCases
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen.components.ImcItem
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen.components.OrderSection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImcDataScreenPreview() {
    ImcDataScreen(navController = rememberNavController(), viewModel())
}

@ExperimentalAnimationApi
@Composable
fun ImcDataScreen(
    navController: NavController,
    viewModel: ImcDataViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Record")

            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Your Imc Records", style = MaterialTheme.typography.h4)

                IconButton(onClick = { }
                ) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Toggle Icon")

                }
            }

        }

        AnimatedVisibility(
            visible =  state.isToggleMenuVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                state.orderType,
                onOrderChange = {
                    viewModel.onEvent(ImcDataEvent.Order(it))
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.imcList){ imcItem ->

                ImcItem(
                    imcModel = imcItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    onDeleteClick = {
                        // TODO: 9/2/22 create te dialgo here.
                        viewModel.onEvent(ImcDataEvent.DeleteImcRecord(imcItem))
                        var snackBarResult: SnackbarResult? = null
                        scope.launch(Dispatchers.Main) {
                            snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Imc Record Deleted",
                                actionLabel = "Undo"
                            )

                            if (snackBarResult == SnackbarResult.ActionPerformed){
                                viewModel.onEvent(ImcDataEvent.RestoreNote)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))

            }
        }

    }
}