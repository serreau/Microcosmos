package sero.com.microcosmos.view.createjob

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.LocalUserRepository
import sero.com.microcosmos.data.repository.RemoteJobRepository

class CreateJobViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()
    private val localUserRepository : LocalUserRepository = LocalUserRepository()

    fun createJob(context : Context?, name : String) = runBlocking {
        context?.let {
            val userId :String? = localUserRepository.getUserId(it)
            if (userId != null) remoteJobRepository.insert(userId, name) else false
        }
    }
}