package com.appwesome.users.data.repository.repositories

import androidx.lifecycle.LiveData
import com.appwesome.users.data.daos.UserDao
import com.appwesome.users.data.entities.UserEntity
import com.appwesome.users.data.repository.interfaces.UserRepository
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(private val userDao: UserDao) :UserRepository{

    override suspend fun addUser(userEntity: UserEntity) {
       userDao.addUser(userEntity)
    }

    override suspend fun updateUser(userEntity: UserEntity) {
      userDao.updateUser(userEntity)
    }

    override suspend fun deleteUser(userEntity: UserEntity) {
       userDao.deleteUser(userEntity)
    }

    override fun getAllUsers(): LiveData<List<UserEntity>> = userDao.getAllUsers()
}