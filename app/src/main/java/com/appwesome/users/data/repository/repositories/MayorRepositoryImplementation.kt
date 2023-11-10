package com.appwesome.users.data.repository.repositories

import androidx.lifecycle.LiveData
import com.appwesome.users.data.daos.MayorDao
import com.appwesome.users.data.entities.MayorEntity
import com.appwesome.users.data.repository.interfaces.MayorRepository
import javax.inject.Inject

class MayorRepositoryImplementation @Inject constructor(private val mayorDao: MayorDao) : MayorRepository{

    override suspend fun addMayor(mayorEntity: MayorEntity) {
      mayorDao.addMayor(mayorEntity)
    }

    override suspend fun updateMayor(mayorEntity: MayorEntity) {
        mayorDao.updateMayor(mayorEntity)
    }

    override suspend fun deleteMayor(mayorEntity: MayorEntity) {
        mayorDao.deleteMayor(mayorEntity)
    }

    override fun getAllMayor(): LiveData<List<MayorEntity>> = mayorDao.getAllMayor()

    override fun getMayorByState(stateId:Int): LiveData<List<MayorEntity>> = mayorDao.getAllMayorByState(stateId)
}