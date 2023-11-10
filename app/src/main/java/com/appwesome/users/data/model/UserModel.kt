package com.appwesome.users.data.model

data class UserModel(
    val name : String,
    val age : Int,
    val addressStreet : String,
    val addressExteriorNumber : String,
    val addressInteriorNumber : String,
    val neighborhood : String,
    val state : Int,
    val mayor :Int,
    val photo :String,
    val id :Int?=0
)