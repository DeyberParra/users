package com.appwesome.users.di

import android.content.Context
import androidx.room.Room
import com.appwesome.users.data.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    const val DATABASE = "DATABASE"


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : DataBase{
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(dataBase: DataBase) = dataBase.userDao()


    @Provides
    @Singleton
    fun provideStateDao(dataBase: DataBase) = dataBase.stateDao()

    @Provides
    @Singleton
    fun provideMayorDao(dataBase: DataBase) = dataBase.mayorDao()

}