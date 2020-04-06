package sero.com.microcosmos.view.detailjob

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.data.repository.RemoteJobRepository
import sero.com.microcosmos.data.repository.RemoteUserRepository

class DetailJobViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()
    private val remoteUserRepository : RemoteUserRepository = RemoteUserRepository()

    fun get(id : String?) : JobGetResponse? = runBlocking {
        id?.let { remoteJobRepository.get(it) }
    }

    fun setImage(
        context: Context,
        owner: String?,
        image: ImageView
    ) = remoteUserRepository.setImage(context, owner, image)
}