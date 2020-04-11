package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sero.com.microcosmos.data.remote.request.body.CreateOfferBody
import sero.com.microcosmos.data.remote.response.DefaultResponse
import sero.com.microcosmos.data.remote.response.GetOfferResponse

interface OfferRemote {
    @GET("/offer/get")
    suspend fun get() : List<GetOfferResponse>

    @GET("/offer/get/{id}")
    suspend fun get(@Path("id") id : String) : GetOfferResponse

    @GET("/offer/getby/job/{jobid}")
    suspend fun getByJobId(@Path("jobid") id : String) : List<GetOfferResponse>

    @POST("/offer/insert")
    suspend fun insert(@Body job : CreateOfferBody) : DefaultResponse

    @GET("/offer/exist/user/{userid}/job/{jobid}")
    suspend fun exist(@Path("userid") userId : String,
                      @Path("jobid") jobId : String) : Boolean

    @GET("/offer/getby/owner/{userid}/job/{jobid}")
    suspend fun getByUserIdAndJobId(@Path("userid") userId: String,
                            @Path("jobid") jobId: String): List<GetOfferResponse>
}