package com.appwesome.users.core.extension

import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.data.model.StateModel

fun List<StateModel>.decorateStateSpinnerList(defaultText: String, defaultId: Int): List<StateModel> {
    val newList = mutableListOf(StateModel(nameState = defaultText, id = defaultId))
    newList.addAll(this)
    return newList
}

fun List<MayorModel>.decorateMayorSpinnerList(defaultText: String, defaultId: Int): List<MayorModel> {
    val newList = mutableListOf(MayorModel(defaultText, idState = defaultId))
    newList.addAll(this)
    return newList
}


