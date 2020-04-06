package sero.com.microcosmos.view.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.LocalUserRepository

class ProfileViewModel : ViewModel() {
    private val localUserRepository : LocalUserRepository = LocalUserRepository()

    fun disconnect(context: Context) = runBlocking {
        context.let { localUserRepository.disconnect(it) }
    }
}