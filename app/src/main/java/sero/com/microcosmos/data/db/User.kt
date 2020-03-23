package sero.com.microcosmos.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class User(
    @PrimaryKey @ColumnInfo(name = "LOGIN") val login: String,
    @ColumnInfo(name = "PASSWORD") val password: String
)