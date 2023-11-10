package com.appwesome.users.data.repository.interfaces

import androidx.lifecycle.LiveData
import com.appwesome.users.data.entities.MayorEntity

interface MayorRepository {

    suspend fun addMayor(mayorEntity: MayorEntity)

    suspend fun updateMayor(mayorEntity: MayorEntity)

    suspend fun deleteMayor(mayorEntity: MayorEntity)

    fun getAllMayor(): LiveData<List<MayorEntity>>

    fun getMayorByState(stateId:Int) :LiveData<List<MayorEntity>>
}