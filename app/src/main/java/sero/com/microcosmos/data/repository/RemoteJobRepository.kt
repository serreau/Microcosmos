package sero.com.microcosmos.data.repository

import sero.com.microcosmos.data.remote.JobRemote
import sero.com.microcosmos.view.utils.retrofit

class RemoteJobRepository (){

    private val remote = retrofit.create(JobRemote::class.java)

    suspend fun get() = remote.get()

    suspend fun get(state :String, search : String) = remote.get(state, search)
}