package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.calculator_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.InvalidDataException
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.CalculateImc
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.wraper_use_cases.CalculateImcUseCases
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val useCases: CalculateImcUseCases,
    savedStateHandle: SavedStateHandle //when you reach the screen that has this viewmodel instanciated, we can use this class to acces all parameters that has been passed
): ViewModel() {

    /*****************STATES*******************/
    //we will have one state for each field

    private val _nameState = mutableStateOf<CalculatorTextFieldState>(
        CalculatorTextFieldState(
            hintText = "Enter Name..."
        )
    )
    val nameState: State<CalculatorTextFieldState> = _nameState

    private val _weightState = mutableStateOf<CalculatorTextFieldState>(
        CalculatorTextFieldState(
            hintText = "Enter your weight..."
        )
    )
    val weightState: State<CalculatorTextFieldState> = _weightState

    private val _heightState = mutableStateOf<CalculatorTextFieldState>(
        CalculatorTextFieldState(
            hintText = "Enter your weight..."
        )
    )
    val heightState: State<CalculatorTextFieldState> = _heightState

    private val _genderState = mutableStateOf<Gender>(Gender.Female)
    val genderState: State<Gender> = _genderState

    private val _stringResultState = mutableStateOf<CalculatorResultState>(
        CalculatorResultState(
            hint = "Information..."
        )
    )
    val stringResultState: State<CalculatorResultState> = _stringResultState

    private val _numericResult = mutableStateOf<CalculatorResultState>(
        CalculatorResultState(
            hint = "Result..."
        )
    )
    val numericResult: State<CalculatorResultState> = _numericResult

    private val _colorState = mutableStateOf<Int>(ImcModel.colorList.random().toArgb())
    val colorState: State<Int> = _colorState

    private val _eventFlow = MutableSharedFlow<SingleUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class SingleUiEvent{
        data class ShowSnackbar(val message:String): SingleUiEvent()
        object SaveNote: SingleUiEvent()

    }
    /********************OTHERS*********************/
    private var imcCalculated: Double? = null

    private var currentId: Int? = null


    init {
        savedStateHandle.get<Int>("imcId")?.let { imcId ->
            if (imcId != -1){
                viewModelScope.launch(Dispatchers.IO) {
                    useCases.getSingleImcRecord(imcId)?.also { imcModel ->
                        currentId = imcModel.id

                        _nameState.value = nameState.value.copy(
                            text = imcModel.name,
                            isHintVisible = false
                        )
                        _weightState.value = weightState.value.copy(
                            text = imcModel.personWeight.toString(),
                            isHintVisible = false
                        )
                        _heightState.value = heightState.value.copy(
                            text = imcModel.peronHeight.toString()
                        )
                        _genderState.value = imcModel.gender

                        _colorState.value = imcModel.color
                    }
                }
            }
        }
    }



    fun onEvent(event:CalculatorEvent){

        when(event){
            is CalculatorEvent.CalculateImc -> {
                imcCalculated = CalculateImc.invoke(event.weight, event.height)
            }
            is CalculatorEvent.ChangeColor -> {
                when(event.gender){
                    Gender.Female -> {
                        when{
                            event.numericResult < 18.5 -> {_colorState.value = ImcModel.colorList[0].toArgb()}
                            event.numericResult in 18.5..23.9 -> {_colorState.value = ImcModel.colorList[2].toArgb()}
                            event.numericResult in 24.0..28.9 -> {_colorState.value = ImcModel.colorList[1].toArgb()}
                            event.numericResult > 29.0 -> {_colorState.value = ImcModel.colorList[1].toArgb()}
                        }
                    }
                    Gender.Male -> {
                        when{
                            event.numericResult < 18.5 -> {_colorState.value = ImcModel.colorList[0].toArgb()}
                            event.numericResult in 18.5..24.9 -> {_colorState.value = ImcModel.colorList[2].toArgb()}
                            event.numericResult in 25.0..28.9 -> {_colorState.value = ImcModel.colorList[1].toArgb()}
                            event.numericResult > 30.0 -> {_colorState.value = ImcModel.colorList[1].toArgb()}
                        }
                    }
                }


            }
            is CalculatorEvent.ChangeHeightFocus -> {
                _heightState.value = heightState.value.copy(
                    isHintVisible = !event.focusState.isFocused && heightState.value.text.isBlank()
                )
            }
            is CalculatorEvent.ChangeNameFocus -> {
                _nameState.value = nameState.value.copy(
                    isHintVisible = !event.focusState.isFocused && nameState.value.text.isBlank()
                )
            }
            is CalculatorEvent.ChangeWeightFocus -> {
                _weightState.value = weightState.value.copy(
                    isHintVisible = !event.focusState.isFocused && weightState.value.text.isBlank()
                )
            }
            is CalculatorEvent.EnterHeight -> {
                _heightState.value = heightState.value.copy(
                    text = event.value
                )
            }
            is CalculatorEvent.EnterName -> {
                _nameState.value = nameState.value.copy(
                    text = event.value
                )
            }
            is CalculatorEvent.EnterWeight -> {
                _weightState.value = weightState.value.copy(
                    text = event.value
                )
            }
            is CalculatorEvent.GenderChange -> {
                _genderState.value = event.gender
            }
            CalculatorEvent.SaveImcRecord -> {
                viewModelScope.launch(Dispatchers.IO) {

                    try {
                        useCases.addImcRecord(
                            ImcModel(
                                name = nameState.value.text,
                                gender = genderState.value,
                                peronHeight = heightState.value.text.toDouble(),
                                personWeight = weightState.value.text.toDouble(),
                                date = getDate(),
                                stringResult = stringResultState.value.text,
                                numericResult = numericResult.value.text.toDouble(),
                                color = colorState.value,
                                id = currentId

                            )
                        )
                        _eventFlow.emit(SingleUiEvent.SaveNote)

                    }catch (e: InvalidDataException){
                        _eventFlow.emit(SingleUiEvent.ShowSnackbar(message = e.message?:"Couldn't save result"))
                    }

                }
            }
        }
    }

    private fun getDate(): String {

        val d = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale("es", "ES"))
        return dateFormat.format(d)
    }

}















































