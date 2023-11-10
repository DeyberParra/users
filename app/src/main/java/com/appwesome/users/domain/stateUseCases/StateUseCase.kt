package com.appwesome.users.domain.stateUseCases

import androidx.lifecycle.LiveData
import com.appwesome.users.core.mappers.toStateEntity
import com.appwesome.users.data.entities.StateEntity
import com.appwesome.users.data.model.StateModel
import com.appwesome.users.data.repository.repositories.StateRepositoryImplementation
import javax.inject.Inject

class StateUseCase @Inject constructor(private val stateRepository: StateRepositoryImplementation){

    suspend fun addState(stateModel: StateModel){
        stateRepository.addState(stateModel.toStateEntity())
    }

    suspend fun updateState(stateModel: StateModel){
        stateRepository.updateState(stateModel.toStateEntity())
    }

    suspend fun deleteState(stateModel: StateModel){
        stateRepository.deleteState(stateModel.toStateEntity())
    }

    fun getAllState() : LiveData<List<StateEntity>> = stateRepository.getAllState()

}