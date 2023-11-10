package com.appwesome.users.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appwesome.users.data.daos.MayorDao
import com.appwesome.users.data.daos.StateDao
import com.appwesome.users.data.daos.UserDao
import com.appwesome.users.data.entities.MayorEntity
import com.appwesome.users.data.entities.StateEntity
import com.appwesome.users.data.entities.UserEntity

@Database(entities = [UserEntity::class, StateEntity::class, MayorEntity::class], version = 1, exportSchema = false)
abstract class  DataBase : RoomDatabase(){

    abstract fun userDao() : UserDao
    abstract fun stateDao() : StateDao
    abstract fun mayorDao() : MayorDao

}