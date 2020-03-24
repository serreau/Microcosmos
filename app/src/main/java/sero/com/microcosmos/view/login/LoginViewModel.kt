package sero.com.microcosmos.view.login

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.LocalUserRepository
import sero.com.microcosmos.data.repository.RemoteUserRepository

class LoginViewModel : ViewModel() {
    private val remoteUserRepository : RemoteUserRepository = RemoteUserRepository()
    private val localUserRepository : LocalUserRepository = LocalUserRepository()

    fun connect(context : Context?, login : String, password : String) =
        runBlocking {
            remoteUserRepository.connect(login, password).also {
                context?.let { rememberMe(it, login, password)}
            }
        }

    private fun rememberMe(context : Context, login : String, password: String) =
        runBlocking {
            localUserRepository.connect(context, login, password)
        }

    fun stillConnected(context : Context?) =
        runBlocking {
            context?.let {
                localUserRepository.stillConnected(it)
            }
        } ?: false
}