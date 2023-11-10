package com.appwesome.users.domain.mayorUseCases

import androidx.lifecycle.LiveData
import com.appwesome.users.core.mappers.toMayorEntity
import com.appwesome.users.data.entities.MayorEntity
import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.data.repository.repositories.MayorRepositoryImplementation
import javax.inject.Inject

class MayorUseCase @Inject constructor(private val mayorRepository: MayorRepositoryImplementation) {

    suspend fun addMayor(mayorModel: MayorModel){
        mayorRepository.addMayor(mayorModel.toMayorEntity())
    }

    suspend fun updateMayor(mayorModel: MayorModel){
        mayorRepository.updateMayor(mayorModel.toMayorEntity())
    }

    suspend fun deleteMayor(mayorModel: MayorModel){
        mayorRepository.deleteMayor(mayorModel.toMayorEntity())
    }

    fun getAllMayor(): LiveData<List<MayorEntity>> = mayorRepository.getAllMayor()

    fun getMayorByState(stateId:Int) : LiveData<List<MayorEntity>> = mayorRepository.getMayorByState(stateId)
}