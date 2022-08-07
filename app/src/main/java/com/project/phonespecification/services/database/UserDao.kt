package com.project.phonespecification.services.database

import androidx.room.*
import com.project.phonespecification.models.database.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE user_name = user_name")
    suspend fun findByUserName(userName: String): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: User)

    @Update
    suspend fun updateUsers(vararg users: User)

    @Delete
    suspend fun delete(user: User)

}