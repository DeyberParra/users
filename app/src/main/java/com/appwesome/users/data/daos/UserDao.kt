package com.appwesome.users.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.appwesome.users.data.entities.UserEntity

@Dao
interface UserDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(userEntity: UserEntity)

    @Update
    suspend fun  updateUser(userEntity:UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM users")
    fun  getAllUsers() : LiveData<List<UserEntity>>

}