package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sero.com.microcosmos.data.remote.request.body.CreateJobBody
import sero.com.microcosmos.data.remote.response.DefaultResponse
import sero.com.microcosmos.data.remote.response.GetJobResponse

interface JobRemote {
    @GET("/job/get")
    suspend fun get() : List<GetJobResponse>

    @GET("/job/get/{id}")
    suspend fun get(@Path("id") id : String) : GetJobResponse

    @GET("/job/get/state/{state}/search/{search}")
    suspend fun get(@Path("state") state : String,
                    @Path("search") search : String) : List<GetJobResponse>

    @POST("/job/insert")
    suspend fun insert(@Body job : CreateJobBody) : DefaultResponse

    @GET("/job/isowner/{userid}/{jobid}")
    suspend fun isOwner(@Path("userid") userId : String,
                        @Path("jobid") jobId : String): DefaultResponse
}