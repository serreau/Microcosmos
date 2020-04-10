package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.JobRemote
import sero.com.microcosmos.data.remote.request.body.CreateJobBody
import sero.com.microcosmos.data.remote.response.GetJobResponse
import sero.com.microcosmos.utils.retrofit

class RemoteJobRepository {

    private val remote = retrofit.create(JobRemote::class.java)

    suspend fun get() = remote.get()

    suspend fun get(id : String) : GetJobResponse = remote.get(id)

    suspend fun get(state :String, search : String) = remote.get(state, search)

    suspend fun insert(owner : String, name : String) = remote.insert(CreateJobBody(owner, name)).success

    suspend fun isOwner(userId: String, jobId: String) = remote.isOwner(userId, jobId).success
}