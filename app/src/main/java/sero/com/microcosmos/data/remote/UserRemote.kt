package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.data.remote.request.body.UserInsertBody
import sero.com.microcosmos.data.remote.response.UserExistResponse
import sero.com.microcosmos.data.remote.response.UserInsertResponse

interface UserRemote {
    @POST("/user/exist")
    suspend fun exist(@Body body : UserExistBody) : UserExistResponse

    @POST("/user/insert")
    suspend fun insert(@Body body : UserInsertBody) : UserInsertResponse

}