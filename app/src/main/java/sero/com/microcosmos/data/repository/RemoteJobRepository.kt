package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.JobRemote
import sero.com.microcosmos.data.remote.request.body.JobCreateBody
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.utils.retrofit

class RemoteJobRepository {

    private val remote = retrofit.create(JobRemote::class.java)

    suspend fun get() = remote.get()

    suspend fun get(id : String) : JobGetResponse = remote.get(id)

    suspend fun get(state :String, search : String) = remote.get(state, search)

    suspend fun insert(owner : String, name : String) =
        remote.insert(JobCreateBody(owner, name)).success
}