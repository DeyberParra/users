package com.appwesome.users.data.repository.repositories

import androidx.lifecycle.LiveData
import com.appwesome.users.data.daos.StateDao
import com.appwesome.users.data.entities.StateEntity
import com.appwesome.users.data.repository.interfaces.StateRepository
import javax.inject.Inject

class StateRepositoryImplementation @Inject constructor(private val stateDao: StateDao) :
    StateRepository {
    override suspend fun addState(stateEntity: StateEntity) {
        stateDao.addState(stateEntity)
    }

    override suspend fun updateState(stateEntity: StateEntity) {
        stateDao.updateState(stateEntity)
    }

    override suspend fun deleteState(stateEntity: StateEntity) {
        stateDao.deleteState(stateEntity)
    }

    override fun getAllState(): LiveData<List<StateEntity>> {
        return stateDao.getAllStates()
    }

    override fun getState(stateId:Int): LiveData<List<StateEntity>> {
        return stateDao.getState(stateId)
    }
}