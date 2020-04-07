package sero.com.microcosmos.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sero.com.microcosmos.data.remote.request.body.OfferCreateBody
import sero.com.microcosmos.data.remote.response.DefaultResponse
import sero.com.microcosmos.data.remote.response.OfferGetResponse

interface OfferRemote {
    @GET("/offer/get")
    suspend fun get() : List<OfferGetResponse>

    @GET("/offer/get/{id}")
    suspend fun get(@Path("id") id : String) : OfferGetResponse

    @POST("/offer/insert")
    suspend fun insert(@Body job : OfferCreateBody) : DefaultResponse
}