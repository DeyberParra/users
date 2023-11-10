package com.appwesome.users.core.patterns

import com.appwesome.users.data.model.MayorModel

object  MayorSingleton {
    private var mayorToUpdate : MayorModel ? = null

    fun setMayorToUpdate(mayor:MayorModel?){
        mayorToUpdate = mayor
    }

    fun getMayorToUpdate():MayorModel? = mayorToUpdate

    fun isUpdateMayor():Boolean{
        return (mayorToUpdate != null)
    }
}