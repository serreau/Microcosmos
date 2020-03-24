package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sero.com.microcosmos.data.remote.request.body.CreateJobBody
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.data.remote.response.JobInsertResponse

interface JobRemote {
    @GET("/job/get")
    suspend fun get() : List<JobGetResponse>

    @GET("/job/get/state/{state}/search/{search}")
    suspend fun get(@Path("state") state : String,
                    @Path("search") search : String) : List<JobGetResponse>

    @POST("/job/insert")
    suspend fun insert(@Body job : CreateJobBody) : JobInsertResponse
}