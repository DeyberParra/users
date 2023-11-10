package com.appwesome.users.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.appwesome.users.data.entities.MayorEntity

@Dao
interface MayorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMayor(mayorEntity : MayorEntity)

    @Update
    suspend fun updateMayor(mayorEntity : MayorEntity)

    @Delete
    suspend fun deleteMayor(mayorEntity: MayorEntity)

    @Query("SELECT * FROM mayor_ships WHERE state_id = :stateId")
    fun getAllMayorByState(stateId:Int) : LiveData<List<MayorEntity>>

    @Query("SELECT * FROM mayor_ships")
    fun getAllMayor(): LiveData<List<MayorEntity>>



}