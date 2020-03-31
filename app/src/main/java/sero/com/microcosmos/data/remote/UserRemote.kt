package sero.com.microcosmos.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.data.remote.request.body.UserInsertBody
import sero.com.microcosmos.data.remote.response.DefaultResponse
import java.io.File

interface UserRemote {
    @POST("/user/exist")
    suspend fun exist(@Body body : UserExistBody) : DefaultResponse

    @POST("/user/insert")
    suspend fun insert(@Body body : UserInsertBody) : DefaultResponse

    @Multipart
    @POST("/user/image")
    suspend fun insert(@Part("owner") owner: RequestBody,
                       @Part body : MultipartBody.Part
    ) : DefaultResponse

    @GET("/user/image/{owner}")
    suspend fun get(@Path("owner") owner : String) : File
}