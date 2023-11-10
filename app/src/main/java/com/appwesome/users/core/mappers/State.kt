package com.appwesome.users.core.mappers

import com.appwesome.users.data.entities.StateEntity
import com.appwesome.users.data.model.StateModel

fun StateModel.toStateEntity(): StateEntity = StateEntity(id?:0, nameState = nameState)

fun StateEntity.toStateModel(): StateModel = StateModel(nameState, id = id)

