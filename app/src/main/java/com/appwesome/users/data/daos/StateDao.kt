package com.appwesome.users.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.appwesome.users.data.entities.StateEntity

@Dao
interface StateDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addState(stateEntity : StateEntity)

   @Update
   suspend fun updateState(stateEntity : StateEntity)

   @Delete
   suspend fun deleteState(stateEntity : StateEntity)

   @Query("SELECT * FROM states")
   fun getAllStates() : LiveData<List<StateEntity>>

   @Query("SELECT * FROM states WHERE id = :stateId")
   fun getState(stateId : Int) : LiveData<List<StateEntity>>
}