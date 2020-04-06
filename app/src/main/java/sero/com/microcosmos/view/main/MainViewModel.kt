package sero.com.microcosmos.view.main

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.LocalUserRepository

class MainViewModel : ViewModel() {
    private val localUserRepository : LocalUserRepository = LocalUserRepository()

    fun stillConnected(context : Context?) =
        runBlocking {
            context?.let {
                localUserRepository.stillConnected(it)
            }
        } ?: false
}