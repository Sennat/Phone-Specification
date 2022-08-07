package com.project.phonespecification.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.phonespecification.services.database.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    /*@Singleton
    suspend fun roomBuilder() {
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        ).build()

        val userDao = db.userDao()
        //val users: List<User> = userDao.getAll() //Later I call this
    }*/
}