package sero.com.microcosmos.view.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.RemoteJobRepository

class SearchViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()

    fun get() = runBlocking { remoteJobRepository.get() }

}