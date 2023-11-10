package com.appwesome.users.data.repository.interfaces

import androidx.lifecycle.LiveData
import com.appwesome.users.data.entities.StateEntity

interface StateRepository {

    suspend fun addState(stateEntity: StateEntity)

    suspend fun updateState(stateEntity: StateEntity)

    suspend fun deleteState(stateEntity: StateEntity)

    fun getAllState(): LiveData<List<StateEntity>>

    fun getState(stateId:Int) : LiveData<List<StateEntity>>

}