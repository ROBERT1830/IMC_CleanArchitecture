package com.robertconstantindinescu.imc_cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen.CalculatorScreen
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen.ImcDataScreen
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.util.ScreenNavigation
import com.robertconstantindinescu.imc_cleanarchitecture.ui.theme.IMC_CleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMC_CleanArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNavigation.ImcDataScreen.route
                    ){
                        composable(route = ScreenNavigation.ImcDataScreen.route){
                            ImcDataScreen(navController = navController)
                        }

                        composable(
                            route = ScreenNavigation.CalculatorScreen.route +
                            "?imcId={imcId}&colorId={colorId}",

                            arguments = listOf(
                                navArgument(name = "imcId"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(name = "colorId"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )

                        ){
                            val color = it.arguments?.getInt("colorId")?:-1
                            CalculatorScreen(navController = navController, color =color)
                        }
                    }


                }
            }
        }
    }
}

