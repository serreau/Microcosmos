package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.OfferRemote
import sero.com.microcosmos.data.remote.request.body.OfferCreateBody
import sero.com.microcosmos.data.remote.response.OfferGetResponse
import sero.com.microcosmos.utils.retrofit

class RemoteOfferRepository {

    private val remote = retrofit.create(OfferRemote::class.java)

    suspend fun get() = remote.get()

    suspend fun get(id : String) : OfferGetResponse = remote.get(id)

    suspend fun insert(owner: String, jobId: String, price: Int)
            = remote.insert(OfferCreateBody(owner, jobId, price)).success
}