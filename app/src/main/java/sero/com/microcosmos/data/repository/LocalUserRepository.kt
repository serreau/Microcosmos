package sero.com.microcosmos.data.repository

import android.content.Context
import sero.com.microcosmos.data.db.CustomDatabase
import sero.com.microcosmos.data.db.User

class LocalUserRepository {
    suspend fun connect(context : Context, login : String, password : String) =
        CustomDatabase.getDatabase(context).userDao().connect(User(login, password))

    suspend fun disconnect(context : Context) =
        CustomDatabase.getDatabase(context).userDao().disconnect()

    suspend fun stillConnected(context : Context) =
        CustomDatabase.getDatabase(context).userDao().isConnected() > 0
}