package sero.com.microcosmos.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sero.com.microcosmos.data.remote.UserRemote
import sero.com.microcosmos.data.remote.request.body.ConnectBody

class RemoteUserRepository (){
    companion object{
        const val BASE_URL = "http://www.microcosmos.org"
        //const val BASE_URL = "http://localhost:3000"
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val remote = retrofit.create(UserRemote::class.java)

    suspend fun connect(login : String, password : String) =
        remote.connect(ConnectBody(login, password)).connected
}