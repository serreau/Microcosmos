package sero.com.microcosmos.data.remote

import retrofit2.http.GET
import sero.com.microcosmos.data.remote.response.JobGetResponse

interface JobRemote {
    @GET("/job/get")
    suspend fun get() : List<JobGetResponse>
}