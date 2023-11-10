package com.appwesome.users.domain.userUseCases

import androidx.lifecycle.LiveData
import com.appwesome.users.core.mappers.toUserEntity
import com.appwesome.users.data.entities.UserEntity
import com.appwesome.users.data.model.UserModel
import com.appwesome.users.data.repository.repositories.UserRepositoryImplementation
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepositoryImplementation) {

    suspend fun addUser(userModel: UserModel){
        userRepository.addUser(userModel.toUserEntity())
    }

    suspend fun updateUser(userModel: UserModel){
        userRepository.updateUser(userModel.toUserEntity())
    }

    suspend fun deleteUser(userModel: UserModel){
        userRepository.deleteUser(userModel.toUserEntity())
    }

    fun getAllUsers(): LiveData<List<UserEntity>> = userRepository.getAllUsers()

}