package sero.com.microcosmos.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
 abstract class CustomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: CustomDatabase? = null

        fun getDatabase(context: Context): CustomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CustomDatabase::class.java,
                    "microcosmos_database"
                ).build()
                return INSTANCE as CustomDatabase
            }
        }
    }
}