package com.appwesome.users.data.repository.interfaces

import androidx.lifecycle.LiveData
import com.appwesome.users.data.entities.UserEntity

interface UserRepository {

    suspend fun addUser(userEntity:UserEntity)

    suspend fun updateUser(userEntity: UserEntity)

    suspend fun deleteUser(userEntity: UserEntity)

    fun getAllUsers():LiveData<List<UserEntity>>

}