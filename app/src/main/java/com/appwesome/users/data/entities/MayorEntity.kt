package com.appwesome.users.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//Alcaldia
@Entity(
    tableName = "mayor_ships",
    foreignKeys = [
        ForeignKey(
            entity = StateEntity::class,
            parentColumns = ["id"],
            childColumns = ["state_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MayorEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "name_mayor")
    val nameMayor :String,
    @ColumnInfo("state_id")
    val idState : Int
)
