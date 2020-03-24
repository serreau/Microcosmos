package sero.com.microcosmos.data.db

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT COUNT(*) FROM USER LIMIT 1")
    suspend fun isConnected(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun connect(user : User)

    @Query("DELETE FROM USER")
    suspend fun disconnect()

    @Query("SELECT login FROM USER LIMIT 1")
    suspend fun getName() : String
}

