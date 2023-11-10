package com.appwesome.users.core.patterns

import com.appwesome.users.data.model.UserModel

object UserSingleton {
    private var user : UserModel? = null

    fun setUser(userModel: UserModel?){
        user = userModel
    }

    fun getUser():UserModel? = user

    fun isNotEmpty():Boolean = (user != null)


}