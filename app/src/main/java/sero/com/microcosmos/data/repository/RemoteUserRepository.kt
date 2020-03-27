package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.UserRemote
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.data.remote.request.body.UserInsertBody
import sero.com.microcosmos.utils.retrofit

class RemoteUserRepository {
    private val remote = retrofit.create(UserRemote::class.java)

    suspend fun connect(login : String, password : String) =
        remote.exist(UserExistBody(login, password)).exist

    suspend fun insert(firstname : String,
                       lastname : String,
                       phone : String,
                       mail : String,
                       password : String) =
        remote.insert(UserInsertBody(firstname, lastname, phone, mail, password)).success
}