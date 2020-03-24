package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.data.remote.response.UserExistResponse

interface UserRemote {
    @POST("/user/exist")
    suspend fun userExist(@Body body : UserExistBody) : UserExistResponse

}