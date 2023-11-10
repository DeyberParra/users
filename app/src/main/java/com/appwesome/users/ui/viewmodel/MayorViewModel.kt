package com.appwesome.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwesome.users.data.entities.MayorEntity
import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.domain.mayorUseCases.MayorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MayorViewModel @Inject constructor( private val mayorUseCase: MayorUseCase) : ViewModel() {

    private val allMayorData: LiveData<List<MayorEntity>> = mayorUseCase.getAllMayor()
    private val mayorSelected  = MutableLiveData<MayorModel>()


    fun addMayor(mayorModel: MayorModel){
        viewModelScope.launch {
            mayorUseCase.addMayor(mayorModel)
        }
    }

    fun updateMayor(mayorModel: MayorModel){
        viewModelScope.launch {
            mayorUseCase.updateMayor(mayorModel)
        }
    }

    fun deleteMayor(mayorModel: MayorModel){
        viewModelScope.launch {
            mayorUseCase.deleteMayor(mayorModel)
        }
    }

    fun getAllMayor():LiveData<List<MayorEntity>> = allMayorData

    fun getAllMayorsByState (idState:Int): LiveData<List<MayorEntity>> = mayorUseCase.getMayorByState(idState)


    fun setMayorSelected(mayorModel: MayorModel){
        mayorSelected.value = mayorModel
    }


}