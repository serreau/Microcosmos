package sero.com.microcosmos.view.main

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.LocalUserRepository

class MainViewModel : ViewModel() {
    fun disconnect(context: Context) = runBlocking {
        context?.let { LocalUserRepository().disconnect(it) }
    }
}