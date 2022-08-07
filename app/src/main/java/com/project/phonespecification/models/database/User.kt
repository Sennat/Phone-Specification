package com.project.phonespecification.models.database

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity(tableName = "users", primaryKeys = ["username"])
data class User(
    @ColumnInfo(name = "user_name") val username: String?,
    @ColumnInfo(name = "user_password") val password: String?,
    @Ignore val picture: Bitmap?
)
