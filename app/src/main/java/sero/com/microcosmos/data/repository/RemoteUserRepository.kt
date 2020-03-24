package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.UserRemote
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.utils.retrofit

class RemoteUserRepository (){
    private val remote = retrofit.create(UserRemote::class.java)

    suspend fun connect(login : String, password : String) =
        remote.userExist(UserExistBody(login, password)).exist
}