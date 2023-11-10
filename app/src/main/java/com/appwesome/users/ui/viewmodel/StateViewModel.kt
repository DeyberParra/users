package com.appwesome.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwesome.users.data.entities.StateEntity
import com.appwesome.users.data.model.StateModel
import com.appwesome.users.domain.stateUseCases.StateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateViewModel @Inject constructor(private val stateUseCase: StateUseCase): ViewModel() {

    private val stateData  : LiveData<List<StateEntity>> = stateUseCase.getAllState()
    private var stateSelected : StateModel ? = null
    private var stateToUpdate : StateModel? = null


    fun stateData(): LiveData<List<StateEntity>> = stateData

    fun addState(stateModel: StateModel){
        viewModelScope.launch {
            stateUseCase.addState(stateModel)
        }
    }

    fun deleteState(state: StateModel){
        viewModelScope.launch {
            stateUseCase.deleteState(state)
        }
    }

    fun setStateToUpdate(state: StateModel){
        stateToUpdate = state
    }
    fun getStateToUpdate () : StateModel? = stateToUpdate

    fun updateState(state: StateModel){
        viewModelScope.launch {
            stateUseCase.updateState(state)
        }
    }

    fun setStateSelected(state: StateModel){
        stateSelected = state
    }

    fun getStateSelected():StateModel? = stateSelected

}