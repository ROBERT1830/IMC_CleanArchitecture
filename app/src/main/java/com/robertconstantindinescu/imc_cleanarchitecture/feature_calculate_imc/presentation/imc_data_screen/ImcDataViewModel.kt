package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.presentation.imc_data_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case.wraper_use_cases.CalculateImcUseCases
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.AscDescOrder
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.ImcOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ImcDataViewModel @Inject constructor( //ww have to use @Inject because the CalculateImcUseCases is in the ImcApp
    private val useCases: CalculateImcUseCases
): ViewModel() {

    /******************STATES*******************/

    private val _state: MutableState<ImcDataState> = mutableStateOf(ImcDataState())
    val state: State<ImcDataState> = _state

    /*****************OTHERS*******************/

    private var lastDeletedImcRecord: ImcModel? = null
    private var currentCoroutineJob: Job? = null

    init {
        getNewImcData(ImcOrder.Date(AscDescOrder.Descending))
    }



    fun onEvent(
        event: ImcDataEvent
    ){
        when(event){
            is ImcDataEvent.Order -> {

                if (state.value.orderType.ascDescOrder == event.imcOrderType.ascDescOrder &&
                        state.value.orderType::class == event.imcOrderType::class){
                    return
                }

                getNewImcData(event.imcOrderType)

            }
            is ImcDataEvent.DeleteImcRecord -> {

                viewModelScope.launch(Dispatchers.IO) {
                    useCases.deleteImcRecord(event.imcModel)
                    lastDeletedImcRecord = event.imcModel
                }
            }
            ImcDataEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isToggleMenuVisible = !state.value.isToggleMenuVisible
                )
            }
            ImcDataEvent.RestoreNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    //here don't need the validation that the fields are ok, because if we delete
                    //one record that was in the db. Is suppose to be fully ok.
                    useCases.addImcRecord(lastDeletedImcRecord ?: return@launch)
                    lastDeletedImcRecord = null

                }
            }

            ImcDataEvent.DeleteDialog -> {
                _state.value = state.value.copy(
                    isDeleteDialogVisible = !state.value.isDeleteDialogVisible
                )
            }
        }

    }

    private fun getNewImcData(imcOrderType: ImcOrder) {

        currentCoroutineJob?.cancel()

        currentCoroutineJob = useCases.getImcRecords(imcOrderType)
            .onEach { imcList ->
                _state.value = state.value.copy(
                    imcList = imcList,
                    orderType = imcOrderType
                )
            }
            .launchIn(viewModelScope)
    }
}