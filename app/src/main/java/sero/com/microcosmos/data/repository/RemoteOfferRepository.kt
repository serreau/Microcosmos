package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.OfferRemote
import sero.com.microcosmos.data.remote.request.body.CreateOfferBody
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.utils.retrofit

class RemoteOfferRepository {

    private val remote = retrofit.create(OfferRemote::class.java)

    suspend fun get() = remote.get()

    suspend fun get(id : String) : GetOfferResponse = remote.get(id)

    suspend fun getByJobId(id : String) = remote.getByJobId(id)

    suspend fun getByUserIdAndJobId(userId: String, jobId: String)
            = remote.getByUserIdAndJobId(userId, jobId)

    suspend fun insert(owner: String, jobId: String, price: Int)
            = remote.insert(CreateOfferBody(owner, jobId, price)).success

    suspend fun exist(userId: String, jobId: String) : Boolean
            = remote.exist(userId, jobId)
}