package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import sero.com.microcosmos.data.remote.request.body.ConnectBody
import sero.com.microcosmos.data.remote.response.ConnectResponse

interface UserRemote {
    @POST("/user/connect")
    suspend fun connect(@Body body : ConnectBody) : ConnectResponse

}