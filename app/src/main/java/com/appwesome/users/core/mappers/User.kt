package com.appwesome.users.core.mappers

import com.appwesome.users.data.entities.UserEntity
import com.appwesome.users.data.model.UserModel

fun UserModel.toUserEntity() : UserEntity = UserEntity(
    id = id?:0,
    name = name,
    age = age,
    addressStreet = addressStreet,
    addressExteriorNumber = addressExteriorNumber,
    addressInteriorNumber = addressInteriorNumber,
    neighborhood = neighborhood,
    state = state,
    mayor = mayor,
    photo = photo
)

fun UserEntity.toUserModel() : UserModel = UserModel(
    name = name,
    age = age,
    addressStreet = addressStreet,
    addressExteriorNumber = addressExteriorNumber,
    addressInteriorNumber = addressInteriorNumber,
    neighborhood = neighborhood,
    state = state,
    mayor = mayor,
    photo = photo,
    id = id
)