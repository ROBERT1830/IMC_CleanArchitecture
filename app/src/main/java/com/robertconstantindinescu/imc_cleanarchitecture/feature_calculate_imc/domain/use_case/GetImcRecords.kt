package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.use_case

import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model.ImcModel
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.respository.ImcRepositoryInterface
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.AscDescOrder
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.ImcOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * This class will get and sort the imc saved data from room
 */
class GetImcRecords (

    private val repo: ImcRepositoryInterface
        ) {

    operator fun invoke(
        imcOrderType: ImcOrder = ImcOrder.Date(AscDescOrder.Descending)
    ):Flow<List<ImcModel>>{

        //Get records and filter them
        return repo.getAllImcRecords().map { imcRecords ->
            when(imcOrderType.ascDescOrder){
                is AscDescOrder.Descending -> {
                    when(imcOrderType){
                        is ImcOrder.Color -> imcRecords.sortedByDescending { imcModel -> imcModel.color}
                        is ImcOrder.Date -> imcRecords.sortedByDescending { imcModel -> imcModel.date }
                    }
                }
                is AscDescOrder.Ascending -> {
                    when(imcOrderType){
                        is ImcOrder.Color -> imcRecords.sortedBy { imcModel -> imcModel.color}
                        is ImcOrder.Date -> imcRecords.sortedBy { imcModel -> imcModel.date }
                    }
                }
            }
        }

    }
}