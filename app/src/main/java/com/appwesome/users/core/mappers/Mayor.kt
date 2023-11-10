package com.appwesome.users.core.mappers

import com.appwesome.users.data.entities.MayorEntity
import com.appwesome.users.data.model.MayorModel

fun MayorModel.toMayorEntity() : MayorEntity = MayorEntity(id?:0,nameMayor, idState)

fun MayorEntity.toMayorModel() : MayorModel = MayorModel(nameMayor, idState, id = id)
