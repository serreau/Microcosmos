package sero.com.microcosmos.view.search

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.RemoteJobRepository
import sero.com.microcosmos.data.repository.RemoteUserRepository
import sero.com.microcosmos.utils.State

class SearchViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()
    private val remoteUserRepository : RemoteUserRepository = RemoteUserRepository()

    fun get() = runBlocking { remoteJobRepository.get() }

    fun getSearch(search : String = "") = runBlocking { remoteJobRepository.get(State.TODO.name, search) }

    fun setImage(
        context: Context,
        owner: String?,
        image: ImageView
    ) = remoteUserRepository.setImage(context, owner, image)

}