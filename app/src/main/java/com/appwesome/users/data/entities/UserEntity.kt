package com.appwesome.users.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo("name")
    val name : String,
    @ColumnInfo("age")
    val age : Int,
    @ColumnInfo("address_street")
    val addressStreet : String,
    @ColumnInfo("address_exterior_number")
    val addressExteriorNumber : String,
    @ColumnInfo("address_interior_number")
    val addressInteriorNumber : String,
    @ColumnInfo("neighborhood")
    val neighborhood :String,
    @ColumnInfo("state_id")
    val state : Int,
    @ColumnInfo("mayor_id")
    val mayor :Int,
    @ColumnInfo("photo")
    val photo :String
)