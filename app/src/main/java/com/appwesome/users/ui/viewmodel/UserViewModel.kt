package com.appwesome.users.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appwesome.users.data.entities.UserEntity
import com.appwesome.users.data.model.UserModel
import com.appwesome.users.domain.userUseCases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) :ViewModel() {

    private val userSelected  = MutableLiveData<UserModel>()
    private var userPhoto : String? = null


    fun addUser(userModel: UserModel){
        viewModelScope.launch {
            userUseCase.addUser(userModel)
        }
    }

    fun updateUser(userModel: UserModel){
        viewModelScope.launch {
            userUseCase.updateUser(userModel)
        }
    }

    fun deleteUser(userModel: UserModel){
        viewModelScope.launch {
            userUseCase.deleteUser(userModel)
        }
    }

    fun getAllUsers(): LiveData<List<UserEntity>> = userUseCase.getAllUsers()

    fun setUserSelected(userModel: UserModel){
        userSelected.value = userModel
    }

    fun setUserPhoto(photo : String){
        userPhoto = photo
    }

    fun getUserPhoto():String? = userPhoto

}