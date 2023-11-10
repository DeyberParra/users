package com.appwesome.users.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Estado
@Entity(tableName = "states")
data class StateEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo("name_state") val nameState : String
)
