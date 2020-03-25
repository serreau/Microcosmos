package sero.com.microcosmos.view.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.RemoteJobRepository
import sero.com.microcosmos.utils.State

class SearchViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()

    fun get() = runBlocking { remoteJobRepository.get() }

    fun getSearch(search : String = "") = runBlocking { remoteJobRepository.get(State.TODO.name, search) }
}